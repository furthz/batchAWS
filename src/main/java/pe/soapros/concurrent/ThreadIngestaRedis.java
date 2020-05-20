package pe.soapros.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import pe.soapros.bean.Contrato;
import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.util.InformeUtil;
import pe.soapros.util.PropertiesUtil;
import redis.clients.jedis.Jedis;

public class ThreadIngestaRedis implements Runnable {

	private static final Log log = Log.getInstance(ThreadIngestaRedis.class);

	private File fileInput;

	private Jedis jedis;

	@Override
	public void run() {
		log.debug("Ejecución del hilo Redis: " + Thread.currentThread().getName());

		final String[] batchJobConfig = { "/spring/batch/config/context.xml" };
		final ApplicationContext context = new ClassPathXmlApplicationContext(batchJobConfig);

		try {

			final PropertiesUtil property = PropertiesUtil.getInstance();

			List<Contrato> lstContratos = new ArrayList<Contrato>();

			// cargar todas las lineas a objetos
			try (Scanner scanner = new Scanner(this.fileInput)) {
				while (scanner.hasNext()) {
					try {
						String linea = scanner.nextLine();
						Contrato cont = InformeUtil.loadLineContrato(linea);
						lstContratos.add(cont);
					} catch (Exception e) {
						log.error("INPUT MALFORMED: " + scanner.nextLine());
					}
				}
			}

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			for (Contrato cont : lstContratos) {		
				String json = ow.writeValueAsString(cont);
				this.jedis.set("bn_ripley_" + cont.getId() + cont.getPan() + "-" + cont.getPeriodo(), json);
			}

		} catch (ExecutionBatchException e) {
			log.error(new StringBuilder("Error to execute File Gen: ").append(this.fileInput.getName()), e);
			throw new ExecutionBatchException(e);
		} catch (Exception e) {
			log.error(new StringBuilder("Error to execute File Gen: ").append(this.fileInput.getName()), e);
			throw new ExecutionBatchException(e);
		} finally {
			// fecha recursos
			if (context != null) {
				((ConfigurableApplicationContext) context).close();
			}
		}

	}

	public ThreadIngestaRedis(final File input, final Jedis jedis) {
		this.fileInput = input;
		this.jedis = jedis;
	}

}
