package pe.soapros.bean;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

@DynamoDBDocument
public class ProductoMetadata implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4771035331413256238L;

	private String correlativo;
	private String fechaPago;
	private String fechaPago_integer;
	private String fechaSaldo;
	private String fechaUltimoClickECI;
	private String fechaVigencia;
	private String marca_procesamiento_recepcion;
	private String montoAPagar;
	private String numeroContrato;
	private String numeroDNICliente;
	private String pagoMinimo;
	private String peso;
	private String razonSocialCliente;
	private String saldo;
	private String tarjetaCreditoCliente;
	private String tipoEnvio;
	private String tipoTarjeta;
	private String totalPaginasEC;
	
	@DynamoDBHashKey(attributeName = "correlativo")
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	@DynamoDBHashKey(attributeName = "fechaPago")
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	@DynamoDBHashKey(attributeName = "fechaPago_integer")
	public String getFechaPago_integer() {
		return fechaPago_integer;
	}
	public void setFechaPago_integer(String fechaPago_integer) {
		this.fechaPago_integer = fechaPago_integer;
	}
	
	@DynamoDBHashKey(attributeName = "fechaSaldo")
	public String getFechaSaldo() {
		return fechaSaldo;
	}
	public void setFechaSaldo(String fechaSaldo) {
		this.fechaSaldo = fechaSaldo;
	}
	
	@DynamoDBHashKey(attributeName = "fechaUltimoClickECI")
	public String getFechaUltimoClickECI() {
		return fechaUltimoClickECI;
	}
	public void setFechaUltimoClickECI(String fechaUltimoClickECI) {
		this.fechaUltimoClickECI = fechaUltimoClickECI;
	}
	
	@DynamoDBHashKey(attributeName = "fechaVigencia")
	public String getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	
	@DynamoDBHashKey(attributeName = "marca_procesamiento_recepcion")
	public String getMarca_procesamiento_recepcion() {
		return marca_procesamiento_recepcion;
	}
	public void setMarca_procesamiento_recepcion(String marca_procesamiento_recepcion) {
		this.marca_procesamiento_recepcion = marca_procesamiento_recepcion;
	}
	
	@DynamoDBHashKey(attributeName = "montoAPagar")
	public String getMontoAPagar() {
		return montoAPagar;
	}
	public void setMontoAPagar(String montoAPagar) {
		this.montoAPagar = montoAPagar;
	}
	
	@DynamoDBHashKey(attributeName = "numeroContrato")
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	
	@DynamoDBHashKey(attributeName = "numeroDNICliente")
	public String getNumeroDNICliente() {
		return numeroDNICliente;
	}
	public void setNumeroDNICliente(String numeroDNICliente) {
		this.numeroDNICliente = numeroDNICliente;
	}
	
	@DynamoDBHashKey(attributeName = "pagoMinimo")
	public String getPagoMinimo() {
		return pagoMinimo;
	}
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}
	
	@DynamoDBHashKey(attributeName = "peso")
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	@DynamoDBHashKey(attributeName = "razonSocialCliente")
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}
	
	@DynamoDBHashKey(attributeName = "saldo")
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
	@DynamoDBHashKey(attributeName = "tarjetaCreditoCliente")
	public String getTarjetaCreditoCliente() {
		return tarjetaCreditoCliente;
	}
	public void setTarjetaCreditoCliente(String tarjetaCreditoCliente) {
		this.tarjetaCreditoCliente = tarjetaCreditoCliente;
	}
	
	@DynamoDBHashKey(attributeName = "tipoEnvio")
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	
	@DynamoDBHashKey(attributeName = "tipoTarjeta")
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	
	@DynamoDBHashKey(attributeName = "totalPaginasEC")
	public String getTotalPaginasEC() {
		return totalPaginasEC;
	}
	public void setTotalPaginasEC(String totalPaginasEC) {
		this.totalPaginasEC = totalPaginasEC;
	}
	
	
}
