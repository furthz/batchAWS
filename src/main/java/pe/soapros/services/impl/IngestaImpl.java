package pe.soapros.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import pe.soapros.bean.Informe;
import pe.soapros.concurrent.ThreadIngesta;
import pe.soapros.exception.ConcurrencyException;
import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.services.Ingesta;
import pe.soapros.util.PropertiesUtil;

@Component(value = "IngestaImpl")
public class IngestaImpl implements Ingesta {

	private static final Log log = Log.getInstance(IngestaImpl.class);

	@Override
	public void createParallelProcess(Map<String, Object> parameters) throws ConcurrencyException {
		log.debug("Creando un proceso concurrente para procesar ingesta");

		try {

			final PropertiesUtil property = PropertiesUtil.getInstance();

			final String bucketName = property.getProperty("s3.url.bucket.name");
			log.info("Consultando el Bucket: " + bucketName + "/" + property.getProperty("s3.prefix"));
			
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
									.withRegion(Regions.US_EAST_1)
									.build();
			
			ListObjectsV2Request rqe = new ListObjectsV2Request();
			rqe.setBucketName(bucketName);
			rqe.withPrefix(property.getProperty("s3.prefix"));
						
			s3.listObjectsV2(rqe);
			ListObjectsV2Result result = null; //s3.listObjectsV2(rqe);
			
			List<S3ObjectSummary> lstMetadata = new ArrayList<S3ObjectSummary>();
			
			do {
				result = s3.listObjectsV2(rqe);
				
				List<S3ObjectSummary> objects = result.getObjectSummaries();
				log.info("Cantidad de archivos por procesar: " + objects.size());				
				
		        for (S3ObjectSummary os : objects) {
		        	if(os.getKey().endsWith(".txt2")) {
		        		System.out.println("* " + os.getKey());
		        		lstMetadata.add(os);
		        	}
		        }
				
				rqe.setContinuationToken(result.getNextContinuationToken());
			}while (result.isTruncated());
			

	        log.info("Cantidad de cortes a procesar: " + lstMetadata.size());
	        
	        List<Informe> lstInforme = new ArrayList<Informe>();
	        
	        if(lstMetadata != null && lstMetadata.size() > 0) {
	        	final ExecutorService executorService = Executors.newFixedThreadPool(
						Integer.valueOf(property.getProperty("quantity.threads")));
	        	
	        	boolean isFisico = false;
	        	
	        	for(final S3ObjectSummary input: lstMetadata) {
	        		
	        		Informe inf = new Informe();
	        		inf.setMetadata(input.getKey());
	        		
	        		isFisico = false;
	        		
	        		if(input.getKey().contains("FISICO") ) {
	        			isFisico = true;
	        		}	        		
	        	
	        		log.info("FILE: " + input.getKey());
	        		executorService.execute(new ThreadIngesta(input, isFisico, inf));
	        		lstInforme.add(inf);
	        	}
	        	
	        	executorService.shutdown();
				while (!executorService.isTerminated()) {
				}
				log.debug("procesamiento finalizado");
				
				
	        }
	        log.info("Procesamiento Resultados");
	        log.info("Metadata|cnt|cntDuplicados|cntErrores|cntGuardados|cntNOPDF");
	        for(Informe inf: lstInforme ) {
	        	log.info(inf.toString());
	        }
	        System.out.println("FINALIZADO EL PROCESAMIENTO");
		} catch (ExecutionBatchException e) {
			log.error("", e);
			throw e;
		} catch (RejectedExecutionException e) {
			log.error("", e);
			throw new ConcurrencyException(e);
		} catch (Exception e) {
			log.error("", e);
			throw new ConcurrencyException(e);
		}
	}

}
