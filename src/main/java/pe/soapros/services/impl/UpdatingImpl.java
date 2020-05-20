package pe.soapros.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.concurrent.RejectedExecutionException;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.soapros.bean.Contrato;
import pe.soapros.bean.Producto;
import pe.soapros.exception.ConcurrencyException;
import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.services.Updating;
import pe.soapros.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component(value = "UpdatingImpl")
public class UpdatingImpl implements Updating {

	private static final Log log = Log.getInstance(UpdatingImpl.class);

	@Override
	public void createParallelProcess(Map<String, Object> parameters) throws ConcurrencyException {
		try {

			final PropertiesUtil property = PropertiesUtil.getInstance();

			final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
			
			final DynamoDB dynamoDB = new DynamoDB(client);

			final String tableName = property.getProperty("dynamo.table.name");

			final String indexName = property.getProperty("dynamo.index.name");

			final Long periodo = Long.valueOf(property.getProperty("dynamo.periodo"));

			final String redis = property.getProperty("redis.server");

			final int port = Integer.valueOf(property.getProperty("redis.port"));

			final JedisPool pool = new JedisPool(redis, port);

			Table table = dynamoDB.getTable(tableName);

			Index index = table.getIndex(indexName);

			ItemCollection<QueryOutcome> items = null;

			QuerySpec querySpec = new QuerySpec();

			querySpec.withKeyConditionExpression("custom02 = :indice and periodo = :per").withValueMap(
					new ValueMap().withString(":indice", "bn_ripley_historico").withLong(":per", periodo));

			items = index.query(querySpec);

			Jedis jedis = pool.getResource();

			// int c = 0;
			int pageNum = 0;
			int cantidad = 0;
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			List<Producto> lstFaltantes = new ArrayList<Producto>();

			for (Page<Item, QueryOutcome> page : items.pages()) {
				log.info("Pagina query: " + ++pageNum);
				
				Iterator<Item> iterator = page.iterator();

				while (iterator.hasNext()) {
					cantidad ++;
					
					Producto prod = null;
					try {
						prod = objectMapper.readValue(iterator.next().toJSON(), Producto.class);
						log.info("Producto " + prod.toString());
						
						String jsonCont = jedis.get(prod.getId() + "-" + prod.getPeriodo());

						Contrato cont = objectMapper.readValue(jsonCont, Contrato.class);
						log.info("Contrato: " + cont.toString());
						
						updateDynamo(prod, cont, table);

					} catch (Exception e) {
						log.error("No Coincide DB: " + prod.getMetadata().getNumeroDNICliente()+ "|" + prod.getPeriodo());
						lstFaltantes.add(prod);
					}
				}

			}
			log.info("Cantidad de registros query: " + cantidad);
			
			for(Producto prod: lstFaltantes) {
				log.error(prod.toString());
			}

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

	private void updateDynamo(Producto prod, Contrato cont, Table table) {
		// validar si coinciden el producto de la base de datos con el contrato enviado

		try {

			if (prod.getId().equals(cont.getIdProducto()) && prod.getPeriodo().intValue() == cont.getPeriodo().intValue()) {

				UpdateItemSpec updateItemSpec = new UpdateItemSpec()
						.withPrimaryKey("id", prod.getId(), "periodo", prod.getPeriodo())
						.withUpdateExpression("set custom02 = :contrato")
						.withValueMap(new ValueMap().withString(":contrato", cont.getContrato()))
						.withReturnValues(ReturnValue.UPDATED_NEW);

				UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
				log.info("Update: " + prod);

			} else {
				log.error("NO CONCIDE Cont:" + cont.toString() + " Prod: " + prod.toString());
			}

		} catch (Exception e) {
			log.error(e);
		}
	}

}
