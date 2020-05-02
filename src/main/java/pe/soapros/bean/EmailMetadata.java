package pe.soapros.bean;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class EmailMetadata implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195936618290152154L;

	private String body;
	private Email html;
	
	@DynamoDBAttribute(attributeName = "body")
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@DynamoDBAttribute(attributeName = "html")
	public Email getHtml() {
		return html;
	}
	public void setHtml(Email html) {
		this.html = html;
	}
	
	
}
