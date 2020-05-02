package pe.soapros.bean;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1612852885597046723L;

	private String cuota;
	private String fechaPago;
	private String fechaSaldo;
	private String fechaVigencia;
	private String hash;
	private String mes;
	private String montoAPagar;
	private String montoEfectivoDisponible;
	private String pagoMinimo;
	private String plazo;
	private String primerNombre;
	private String tcea;
	private String url;
	
	@DynamoDBAttribute(attributeName = "cuota")
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	
	@DynamoDBAttribute(attributeName = "fechaPago")
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	@DynamoDBAttribute(attributeName = "fechaSaldo")
	public String getFechaSaldo() {
		return fechaSaldo;
	}
	public void setFechaSaldo(String fechaSaldo) {
		this.fechaSaldo = fechaSaldo;
	}
	
	@DynamoDBAttribute(attributeName = "fechaVigencia")
	public String getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	
	@DynamoDBAttribute(attributeName = "hash")
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	@DynamoDBAttribute(attributeName = "mes")
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	@DynamoDBAttribute(attributeName = "montoAPagar")
	public String getMontoAPagar() {
		return montoAPagar;
	}
	public void setMontoAPagar(String montoAPagar) {
		this.montoAPagar = montoAPagar;
	}
	
	@DynamoDBAttribute(attributeName = "montoEfectivoDisponible")
	public String getMontoEfectivoDisponible() {
		return montoEfectivoDisponible;
	}
	public void setMontoEfectivoDisponible(String montoEfectivoDisponible) {
		this.montoEfectivoDisponible = montoEfectivoDisponible;
	}
	
	@DynamoDBAttribute(attributeName = "pagoMinimo")
	public String getPagoMinimo() {
		return pagoMinimo;
	}
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}
	
	@DynamoDBAttribute(attributeName = "plazo")
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	
	@DynamoDBAttribute(attributeName = "primerNombre")
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	
	@DynamoDBAttribute(attributeName = "tcea")
	public String getTcea() {
		return tcea;
	}
	public void setTcea(String tcea) {
		this.tcea = tcea;
	}
	
	@DynamoDBAttribute(attributeName = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
