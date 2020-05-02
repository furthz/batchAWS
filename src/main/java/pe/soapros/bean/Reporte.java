package pe.soapros.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3789070292780861694L;

	private String nomenclatura_pdf;
	private String dni;
	private String num_tarjeta;
	private String corte;
	private String producto;
	private String nombre_cliente;
	private String tipo_envio;
	private String correo_remitente;
	private String correo_destinatario;
	private String estado_entrega;
	private String detalle_estado;
	private Date fecha_hora_envio;
	private Date fecha_hora_entrega;
	private Date fecha_hora_lectura_1;
	private Date fecha_hora_lectura_2;
	private Date fecha_hora_lectura_3;
	private String log_sustento;

	private String s3_url;
	private Long size;

	public String getNomenclatura_pdf() {
		return nomenclatura_pdf;
	}

	public void setNomenclatura_pdf(String nomenclatura_pdf) {
		if (nomenclatura_pdf != null && !nomenclatura_pdf.equals("")) {
			this.nomenclatura_pdf = nomenclatura_pdf;
		}
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni != null && !dni.equals("")) {
			this.dni = dni;
		}
	}

	public String getNum_tarjeta() {
		return num_tarjeta;
	}

	public void setNum_tarjeta(String num_tarjeta) {
		if (num_tarjeta != null && !num_tarjeta.equals("")) {
			this.num_tarjeta = num_tarjeta;
		}
	}

	public String getCorte() {
		return corte;
	}

	public void setCorte(String corte) {
		if (corte != null && !corte.equals("")) {
			this.corte = corte;
		}
	}

	public String getFechaCorte() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = format.parse(this.corte);

		SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");

		return format2.format(date);
	}

	public String getFechaCorteInteger() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = format.parse(this.corte);

		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");

		return format2.format(date);
	}

	public String getPeriodo() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		Date date = format.parse(this.corte);

		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMM");

		return format2.format(date);
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		if (producto != null && !producto.equals("")) {
			this.producto = producto;
		}
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		if (nombre_cliente != null && !nombre_cliente.equals("")) {
			this.nombre_cliente = nombre_cliente;
		}
	}

	public String getTipo_envio() {
		return tipo_envio;
	}

	public void setTipo_envio(String tipo_envio) {
		if (tipo_envio != null && !tipo_envio.equals("")) {
			this.tipo_envio = tipo_envio;
		}
	}

	public String getCorreo_remitente() {
		return correo_remitente;
	}

	public void setCorreo_remitente(String correo_remitente) {
		if (correo_remitente != null && !correo_remitente.equals("")) {
			this.correo_remitente = correo_remitente;
		}
	}

	public String getCorreo_destinatario() {
		return correo_destinatario;
	}

	public void setCorreo_destinatario(String correo_destinatario) {
		if (correo_destinatario != null && !correo_destinatario.equals("")) {
			this.correo_destinatario = correo_destinatario;
		}
	}

	public String getEstado_entrega() {
		return estado_entrega.trim();
	}

	public void setEstado_entrega(String estado_entrega) {
		if (estado_entrega != null && !estado_entrega.equals("")) {
			this.estado_entrega = estado_entrega;
		}
	}

	public String getDetalle_estado() {
		return detalle_estado;
	}

	public void setDetalle_estado(String detalle_estado) {
		if (detalle_estado != null && !detalle_estado.equals("")) {
			this.detalle_estado = detalle_estado;
		}
	}

	public String getLog_sustento() {
		return log_sustento;
	}

	public void setLog_sustento(String log_sustento) {
		this.log_sustento = log_sustento;
	}

	public String getS3_url() {
		return s3_url;
	}

	public void setS3_url(String s3_url) {
		this.s3_url = s3_url;
	}

	public Date getFecha_hora_envio() {
		return fecha_hora_envio;
	}

	public void setFecha_hora_envio(Date fecha_hora_envio) {
		this.fecha_hora_envio = fecha_hora_envio;
	}

	public Date getFecha_hora_entrega() {
		return fecha_hora_entrega;
	}

	public void setFecha_hora_entrega(Date fecha_hora_entrega) {
		this.fecha_hora_entrega = fecha_hora_entrega;
	}

	public Date getFecha_hora_lectura_1() {
		return fecha_hora_lectura_1;
	}

	public void setFecha_hora_lectura_1(Date fecha_hora_lectura_1) {
		this.fecha_hora_lectura_1 = fecha_hora_lectura_1;
	}

	public Date getFecha_hora_lectura_2() {
		return fecha_hora_lectura_2;
	}

	public void setFecha_hora_lectura_2(Date fecha_hora_lectura_2) {
		this.fecha_hora_lectura_2 = fecha_hora_lectura_2;
	}

	public Date getFecha_hora_lectura_3() {
		return fecha_hora_lectura_3;
	}

	public void setFecha_hora_lectura_3(Date fecha_hora_lectura_3) {
		this.fecha_hora_lectura_3 = fecha_hora_lectura_3;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {

		this.size = size;
	}

	public Reporte() {

		this.correo_remitente = "raul.talledo@soapros.pe";
		this.correo_destinatario = "default@enotria.com";
		this.estado_entrega = "Leído";
		this.detalle_estado = "Leído";
		this.fecha_hora_envio = new Date("01/01/2019 00:00:00");
		this.fecha_hora_entrega = new Date("01/01/2019 00:00:00");
		this.fecha_hora_lectura_1 = new Date("01/01/2019 00:00:00");
		this.fecha_hora_lectura_2 = new Date("01/01/2019 00:00:00");
		this.fecha_hora_lectura_3 = new Date("01/01/2019 00:00:00");
		this.log_sustento = "LOG";

	}

	@Override
	public String toString() {
		return "Reporte [nomenclatura_pdf=" + nomenclatura_pdf + ", dni=" + dni + ", s3_url=" + s3_url + "]";
	}

}
