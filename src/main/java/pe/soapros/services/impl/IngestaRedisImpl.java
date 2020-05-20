package pe.soapros.services.impl;

import java.io.File;
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
import pe.soapros.concurrent.ThreadIngestaRedis;
import pe.soapros.exception.ConcurrencyException;
import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.services.IngestaRedis;
import pe.soapros.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component(value = "IngestaRedisImpl")
public class IngestaRedisImpl implements IngestaRedis{
	
	private static final Log log = Log.getInstance(IngestaRedisImpl.class);

	@Override
	public void createParallelProcess(Map<String, Object> parameters) throws ConcurrencyException {
		log.debug("Creando un proceso concurrente para procesar la subida a REDIS");
		
		try {

			final PropertiesUtil property = PropertiesUtil.getInstance();

			final String pathDirectory = property.getProperty("directory.root.files.input");
			
			final String redis = property.getProperty("redis.server");
			
			final int port = Integer.valueOf(property.getProperty("redis.port"));
			
			final JedisPool pool = new JedisPool(redis,port);
			
			final File folder = new File(pathDirectory);
			
			final ExecutorService executorService = Executors.newFixedThreadPool(
					Integer.valueOf(property.getProperty("quantity.threads")));
		
			for(final File fichero: folder.listFiles()) {
				if(!fichero.isDirectory()) {	
					
					Jedis jedis = pool.getResource();
					
					executorService.execute(new ThreadIngestaRedis(fichero, jedis));					
				}
			}
			
			executorService.shutdown();
			while (!executorService.isTerminated()) {
			}
			log.debug("procesamiento finalizado");
			
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
