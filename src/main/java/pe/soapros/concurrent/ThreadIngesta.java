package pe.soapros.concurrent;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import pe.soapros.bean.Informe;
import pe.soapros.bean.Producto;
import pe.soapros.bean.ProductoMovimiento;
import pe.soapros.bean.Reporte;
import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.util.InformeUtil;
import pe.soapros.util.PropertiesUtil;

public class ThreadIngesta implements Runnable {

	private static final Log log = Log.getInstance(ThreadIngesta.class);

	private S3ObjectSummary fileInput;

	private boolean isFisico;

	private Informe info;
	
	@Override
	public void run() {
		log.debug("Ejecución del hilo: " + Thread.currentThread().getName());

		final String[] batchJobConfig = { "/spring/batch/config/context.xml" };
		final ApplicationContext context = new ClassPathXmlApplicationContext(batchJobConfig);

		try {

			final PropertiesUtil property = PropertiesUtil.getInstance();

			final String bucketName = property.getProperty("s3.url.bucket.name");

			URI uri = new URI(this.fileInput.getKey());

			String prefix = uri.resolve(".").toString();

			String prex = uri.resolve("..").toString();

			prex = prex.replaceFirst("metadata/", "bn_ripley/historico/");

			String corte = prefix.substring(prefix.length() - 3, prefix.length() - 1);

			String prexPDF = prefix;
			
			/*
			if (this.isFisico) {
				prexPDF = prex + "FISICO/" + corte + "/";
			} else {
				prexPDF = prex + "VIRTUAL/" + corte + "/";
			}*/

			final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

			final AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

			DynamoDBMapper mapper = new DynamoDBMapper(db);

			S3Object o = s3.getObject(bucketName, this.fileInput.getKey());

			S3ObjectInputStream s3is = o.getObjectContent();

			List<Reporte> lsReportes = InformeUtil.loadReport(s3is);
			log.info("Cantidad de Registros a procesar: " + lsReportes.size());

			int contGuardados = 0;
			int contReplicados = 0;
			int contErrores = 0;
			int contNOPDF = 0;

			for (final Reporte rep : lsReportes) {
				// log.debug("File: " + this.fileInput.getKey() + " - CSV: " + rep.toString());

				boolean existe = InformeUtil.existeFileS3(s3, bucketName, prexPDF + rep.getNomenclatura_pdf());

				if (existe) { // cargar la ruta a la base de datos de producto
					try {

						UUID uuid = UUID.randomUUID();
						String codigo = uuid.toString();

						InformeUtil.validateReport(rep);

						rep.setS3_url("s3@" + bucketName + ":" + prexPDF + rep.getNomenclatura_pdf());
						rep.setSize(InformeUtil.getSizeFileS3(s3, bucketName, prexPDF + rep.getNomenclatura_pdf()));

						Producto prod = InformeUtil.translateProducto(rep, codigo, this.isFisico);

						ProductoMovimiento mov = InformeUtil.translateProductoMovimiento(rep, codigo);

						// Producto findP = mapper.load(Producto.class, prod.getId(),
						// prod.getPeriodo());

						com.amazonaws.services.dynamodbv2.model.Condition cond = new com.amazonaws.services.dynamodbv2.model.Condition()
								.withComparisonOperator(ComparisonOperator.EQ)
								.withAttributeValueList(new AttributeValue().withN(prod.getPeriodo().toString()));

						DynamoDBQueryExpression<Producto> queryExpression = new DynamoDBQueryExpression<Producto>()
								.withHashKeyValues(prod).withRangeKeyCondition("periodo", cond).withLimit(1);

						int cantidad = mapper.count(Producto.class, queryExpression);

						if (cantidad == 0) {

							mapper.save(prod);

							log.info("Producto saved: " + prod.getId());

							mapper.save(mov);
							log.info("Movimiento saved: " + mov.getId());

							contGuardados++;

							//System.out.println("PDF processing: " + rep.getNomenclatura_pdf() + " CSV: "
							//		+ this.fileInput.getKey() + " id: " + prod.toString());

						} else {
							contReplicados++;
							log.warn("Registro ya insertado: " + rep.getNomenclatura_pdf() + " File: "
									+ this.fileInput.getKey() + " id: " + prod.toString());
						}
						// log.info("PDF processing: " + rep.getNomenclatura_pdf() + " File: " +
						// this.fileInput.getKey());

					} catch (Exception e) {
						contErrores++;
						log.error("Error Convert: " + this.fileInput.getKey() + " - CSV: " + rep.toString() + " ", e);
					}

				} else {
					contNOPDF++;
					log.error("No Exist File: " + this.fileInput.getKey() + " PDF: " + prexPDF
							+ rep.getNomenclatura_pdf());
				}

			}

			log.info("Resultado Metadata: " + this.fileInput.getKey() + "Guardados: " + contGuardados + " Duplicados: "
					+ contReplicados + " Errores: " + contErrores + " NOPDF: " + contNOPDF);

			this.info.setCnt(lsReportes.size());
			this.info.setCntGuardados(contGuardados);
			this.info.setCntDuplicados(contReplicados);
			this.info.setCntErrores(contErrores);
			this.info.setCntNOPDF(contNOPDF);

		} catch (ExecutionBatchException e) {
			log.error(new StringBuilder("Error to execute File Gen: ").append(this.fileInput.getKey()), e);
			throw new ExecutionBatchException(e);
		} catch (Exception e) {
			log.error(new StringBuilder("Error to execute File Gen: ").append(this.fileInput.getKey()), e);
			throw new ExecutionBatchException(e);
		} finally {
			// fecha recursos
			if (context != null) {
				((ConfigurableApplicationContext) context).close();
			}
		}

	}

	public ThreadIngesta(final S3ObjectSummary input, final boolean isFisico, final Informe info) {
		this.fileInput = input;
		this.isFisico = isFisico;
		this.info = info;
	}

}
