package pe.soapros.bean;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Recurso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971702633646015604L;

	private String canal;
	private String fecha_expiracion;
	private String nombre;
	private String peso;
	private String ubicacion;
	
	@DynamoDBAttribute(attributeName = "canal")
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	@DynamoDBAttribute(attributeName = "fecha_expiracion")
	public String getFecha_expiracion() {
		return fecha_expiracion;
	}
	public void setFecha_expiracion(String fecha_expiracion) {
		this.fecha_expiracion = fecha_expiracion;
	}
	
	@DynamoDBAttribute(attributeName = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@DynamoDBAttribute(attributeName = "peso")
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	@DynamoDBAttribute(attributeName = "ubicacion")
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
