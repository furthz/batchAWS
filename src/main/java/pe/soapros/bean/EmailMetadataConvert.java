package pe.soapros.bean;

import java.io.IOException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmailMetadataConvert implements DynamoDBTypeConverter<String, EmailMetadata> {

	@Override
	public String convert(EmailMetadata object) {
		
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            String objectsString = objectMapper.writerWithView(EmailMetadata.class).writeValueAsString(object);
            return objectsString;
        } catch (JsonProcessingException e) {
            //do something
        }
        return null;
		
		/*
		EmailMetadata itemEmail = (EmailMetadata) object;
		String mail = null;
		try {
			if (itemEmail != null) {
				mail = String.format("%s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s", 
						itemEmail.getBody(), 
						itemEmail.getHtml().getCuota(), 
						itemEmail.getHtml().getFechaPago(),
						itemEmail.getHtml().getFechaSaldo(), 
						itemEmail.getHtml().getFechaVigencia(),
						itemEmail.getHtml().getHash(), 
						itemEmail.getHtml().getMes(),
						itemEmail.getHtml().getMontoAPagar(), 
						itemEmail.getHtml().getMontoEfectivoDisponible(),
						itemEmail.getHtml().getPagoMinimo(), 
						itemEmail.getHtml().getPlazo(),
						itemEmail.getHtml().getPrimerNombre(), 
						itemEmail.getHtml().getTcea(),
						itemEmail.getHtml().getUrl());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;*/
	}

	@Override
	public EmailMetadata unconvert(String objectsString) {
		
		ObjectMapper objectMapper = new ObjectMapper();
        try {
        	EmailMetadata objects = (EmailMetadata) objectMapper.readValue(objectsString, new TypeReference<EmailMetadata>() {
			});
            return objects;
        } catch (JsonParseException e) {
            //do something
        } catch (JsonMappingException e) {
            //do something
        } catch (IOException e) {
            //do something
        }
        return null;
		
		/*
		EmailMetadata itemEmail = new EmailMetadata();

		try {

			if (s != null && s.length() != 0) {
				String[] data = s.split("x");
				itemEmail.setBody(data[0].trim());
				
				Email mail = new Email();
				mail.setCuota(data[1]);
				mail.setFechaPago(data[2]);
				mail.setFechaSaldo(data[3]);
				mail.setFechaVigencia(data[4]);
				mail.setHash(data[5]);
				mail.setMes(data[6]);
				mail.setMontoAPagar(data[7]);
				mail.setMontoEfectivoDisponible(data[8]);
				mail.setPagoMinimo(data[9]);
				mail.setPlazo(data[10]);
				mail.setPrimerNombre(data[11]);
				mail.setTcea(data[12]);
				mail.setUrl(data[13]);
				
				itemEmail.setHtml(mail);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemEmail;*/
	}

}
