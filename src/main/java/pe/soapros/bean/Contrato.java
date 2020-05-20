package pe.soapros.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5974487196426399631L;
	
	private String pan;
	private String fec_corte;
	private String id;
	private String contrato;
	
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getFec_corte() {
		return fec_corte;
	}
	public void setFec_corte(String fec_corte) {
		this.fec_corte = fec_corte;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	
	public Integer getPeriodo() throws ParseException {
		//int rpta = 0;
		
		//String valor = this.fec_corte.substring(6, 9) + this.fec_corte.substring(3,5); //"10/03/2020"
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = format.parse(this.fec_corte);
		
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMM");
		
		
		
		return Integer.valueOf(format2.format(date));
	}
	
	public String getIdProducto() {
		String cadena = "";
		
		cadena = "bn_ripley_" + this.id + this.pan;
		
		return cadena;
	}
	@Override
	public String toString() {
		return "Contrato [pan=" + pan + ", fec_corte=" + fec_corte + ", id=" + id + ", contrato=" + contrato + "]";
	}
	
	
}
