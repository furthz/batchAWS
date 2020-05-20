package pe.soapros.bean;

import java.io.Serializable;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;

@DynamoDBTable(tableName = "capacniam-producto-prd")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810469099108707892L;

	private String id;

	private Integer periodo;

	private String audit_fecha_creacion;
	private String audit_fecha_modificacion;
	private String audit_usuario_creacion;
	private String audit_usuario_modificacion;
	private String boolean_ultimo_movimiento_estado;
	private Long cantidad_reenvios;
	private String cliente_id;
	private String custom01;
	private String custom02;
	private String custom03;
	private EmailMetadata email_metadata;
	private String error_generacion;
	private Long fecha_corte_integer;
	private String fecha_corte_string;
	private Long fecha_entrega_integer;
	private String fecha_entrega_string;
	private Long fecha_generacion_integer;
	private String fecha_generacion_string;
	private Long fecha_primer_envio_integer;
	private String fecha_primer_envio_string;

	private String instancia_proceso_id;
	private String instancia_proceso_output_id;
	private String instancia_proceso_periodo;
	private String llave;
	private ProductoMetadata metadata;
	private String mimetype;

	private List<String> reclamos;
	private List<Recurso> recursos;
	private String tipo_producto;
	private String tipo_producto_string;
	private String tipo_subproducto;
	private String tipo_subproducto_string;
	private List<String> ultimo_movimiento_destinatario;
	private String ultimo_movimiento_error_envio;
	private String ultimo_movimiento_estado;
	private String ultimo_movimiento_estado_string;
	private Long ultimo_movimiento_fecha_envio_integer;
	private String ultimo_movimiento_fecha_envio_string;
	private String ultimo_movimiento_fecha_hora_envio_string;
	private Long ultimo_movimiento_fecha_lectura_integer;
	private String ultimo_movimiento_fecha_lectura_string;
	private Long ultimo_movimiento_fecha_queja_integer;
	private String ultimo_movimiento_fecha_queja_string;
	private Long ultimo_movimiento_fecha_rebote_integer;
	private String ultimo_movimiento_fecha_rebote_string;
	private Long ultimo_movimiento_fecha_recepcion_integer;
	private String ultimo_movimiento_fecha_recepcion_string;
	private String ultimo_movimiento_id;
	private String ultimo_movimiento_motivo_estado;
	private String ultimo_movimiento_motivo_estado_string;
	private String ultimo_movimiento_nro_cargo_generado;
	private String ultimo_movimiento_periodo;
	private String ultimo_movimiento_remitente;
	private String ultimo_movimiento_ses_message_id;
	private String ultimo_movimiento_tipo;
	private String ultimo_movimiento_tipo_string;
	private String ultimo_movimiento_usuario;
	private String ultimo_reclamo;
	private List<String> visualizaciones;

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBRangeKey(attributeName = "periodo")
	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	@DynamoDBAttribute(attributeName = "audit_fecha_creacion")
	public String getAudit_fecha_creacion() {
		return audit_fecha_creacion;
	}

	public void setAudit_fecha_creacion(String audit_fecha_creacion) {
		this.audit_fecha_creacion = audit_fecha_creacion;
	}

	@DynamoDBAttribute(attributeName = "audit_fecha_modificacion")
	public String getAudit_fecha_modificacion() {
		return audit_fecha_modificacion;
	}

	public void setAudit_fecha_modificacion(String audit_fecha_modificacion) {
		this.audit_fecha_modificacion = audit_fecha_modificacion;
	}

	@DynamoDBAttribute(attributeName = "audit_usuario_creacion")
	public String getAudit_usuario_creacion() {
		return audit_usuario_creacion;
	}

	public void setAudit_usuario_creacion(String audit_usuario_creacion) {
		this.audit_usuario_creacion = audit_usuario_creacion;
	}

	@DynamoDBAttribute(attributeName = "audit_usuario_modificacion")
	public String getAudit_usuario_modificacion() {
		return audit_usuario_modificacion;
	}

	public void setAudit_usuario_modificacion(String audit_usuario_modificacion) {
		this.audit_usuario_modificacion = audit_usuario_modificacion;
	}

	@DynamoDBAttribute(attributeName = "boolean_ultimo_movimiento_estado")
	public String getBoolean_ultimo_movimiento_estado() {
		return boolean_ultimo_movimiento_estado;
	}

	public void setBoolean_ultimo_movimiento_estado(String boolean_ultimo_movimiento_estado) {
		this.boolean_ultimo_movimiento_estado = boolean_ultimo_movimiento_estado;
	}

	@DynamoDBAttribute(attributeName = "cantidad_reenvios")
	public Long getCantidad_reenvios() {
		return cantidad_reenvios;
	}

	public void setCantidad_reenvios(Long cantidad_reenvios) {
		this.cantidad_reenvios = cantidad_reenvios;
	}

	@DynamoDBAttribute(attributeName = "cliente_id")
	public String getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}

	@DynamoDBAttribute(attributeName = "custom01")
	public String getCustom01() {
		return custom01;
	}

	public void setCustom01(String custom01) {
		this.custom01 = custom01;
	}

	@DynamoDBAttribute(attributeName = "custom02")
	public String getCustom02() {
		return custom02;
	}

	public void setCustom02(String custom02) {
		this.custom02 = custom02;
	}

	@DynamoDBAttribute(attributeName = "custom03")
	public String getCustom03() {
		return custom03;
	}

	public void setCustom03(String custom03) {
		this.custom03 = custom03;
	}


	@DynamoDBAttribute(attributeName = "email_metadata")
	public EmailMetadata getEmail_metadata() {
		return email_metadata;
	}

	public void setEmail_metadata(EmailMetadata email_metadata) {
		this.email_metadata = email_metadata;
	}

	@DynamoDBAttribute(attributeName = "error_generacion")
	public String getError_generacion() {
		return error_generacion;
	}

	public void setError_generacion(String error_generacion) {
		this.error_generacion = error_generacion;
	}

	@DynamoDBAttribute(attributeName = "fecha_corte_integer")
	public Long getFecha_corte_integer() {
		return fecha_corte_integer;
	}

	public void setFecha_corte_integer(Long fecha_corte_integer) {
		this.fecha_corte_integer = fecha_corte_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_corte_string")
	public String getFecha_corte_string() {
		return fecha_corte_string;
	}

	public void setFecha_corte_string(String fecha_corte_string) {
		this.fecha_corte_string = fecha_corte_string;
	}

	@DynamoDBAttribute(attributeName = "fecha_entrega_integer")
	public Long getFecha_entrega_integer() {
		return fecha_entrega_integer;
	}

	public void setFecha_entrega_integer(Long fecha_entrega_integer) {
		this.fecha_entrega_integer = fecha_entrega_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_entrega_string")
	public String getFecha_entrega_string() {
		return fecha_entrega_string;
	}

	public void setFecha_entrega_string(String fecha_entrega_string) {
		this.fecha_entrega_string = fecha_entrega_string;
	}

	@DynamoDBAttribute(attributeName = "fecha_generacion_integer")
	public Long getFecha_generacion_integer() {
		return fecha_generacion_integer;
	}

	public void setFecha_generacion_integer(Long fecha_generacion_integer) {
		this.fecha_generacion_integer = fecha_generacion_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_generacion_string")
	public String getFecha_generacion_string() {
		return fecha_generacion_string;
	}

	public void setFecha_generacion_string(String fecha_generacion_string) {
		this.fecha_generacion_string = fecha_generacion_string;
	}

	@DynamoDBAttribute(attributeName = "fecha_primer_envio_integer")
	public Long getFecha_primer_envio_integer() {
		return fecha_primer_envio_integer;
	}

	public void setFecha_primer_envio_integer(Long fecha_primer_envio_integer) {
		this.fecha_primer_envio_integer = fecha_primer_envio_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_primer_envio_string")
	public String getFecha_primer_envio_string() {
		return fecha_primer_envio_string;
	}

	public void setFecha_primer_envio_string(String fecha_primer_envio_string) {
		this.fecha_primer_envio_string = fecha_primer_envio_string;
	}

	@DynamoDBAttribute(attributeName = "instancia_proceso_id")
	public String getInstancia_proceso_id() {
		return instancia_proceso_id;
	}

	public void setInstancia_proceso_id(String instancia_proceso_id) {
		this.instancia_proceso_id = instancia_proceso_id;
	}

	@DynamoDBAttribute(attributeName = "instancia_proceso_output_id")
	public String getInstancia_proceso_output_id() {
		return instancia_proceso_output_id;
	}

	public void setInstancia_proceso_output_id(String instancia_proceso_output_id) {
		this.instancia_proceso_output_id = instancia_proceso_output_id;
	}

	@DynamoDBAttribute(attributeName = "instancia_proceso_periodo")
	public String getInstancia_proceso_periodo() {
		return instancia_proceso_periodo;
	}

	public void setInstancia_proceso_periodo(String instancia_proceso_periodo) {
		this.instancia_proceso_periodo = instancia_proceso_periodo;
	}

	@DynamoDBAttribute(attributeName = "llave")
	public String getLlave() {
		return llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	// @DynamoDBTypeConverted(converter = ProductoMetadataConvert.class)
	@DynamoDBAttribute(attributeName = "metadata")
	public ProductoMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(ProductoMetadata metadata) {
		this.metadata = metadata;
	}

	@DynamoDBAttribute(attributeName = "mimetype")
	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	@DynamoDBAttribute(attributeName = "reclamos")
	public List<String> getReclamos() {
		return reclamos;
	}

	public void setReclamos(List<String> reclamos) {
		this.reclamos = reclamos;
	}

	@DynamoDBAttribute(attributeName = "recursos")
	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	@DynamoDBAttribute(attributeName = "tipo_producto")
	public String getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(String tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	@DynamoDBAttribute(attributeName = "tipo_producto_string")
	public String getTipo_producto_string() {
		return tipo_producto_string;
	}

	public void setTipo_producto_string(String tipo_producto_string) {
		this.tipo_producto_string = tipo_producto_string;
	}

	@DynamoDBAttribute(attributeName = "tipo_subproducto")
	public String getTipo_subproducto() {
		return tipo_subproducto;
	}

	public void setTipo_subproducto(String tipo_subproducto) {
		this.tipo_subproducto = tipo_subproducto;
	}

	@DynamoDBAttribute(attributeName = "tipo_subproducto_string")
	public String getTipo_subproducto_string() {
		return tipo_subproducto_string;
	}

	public void setTipo_subproducto_string(String tipo_subproducto_string) {
		this.tipo_subproducto_string = tipo_subproducto_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_destinatario")
	public List<String> getUltimo_movimiento_destinatario() {
		return ultimo_movimiento_destinatario;
	}

	public void setUltimo_movimiento_destinatario(List<String> ultimo_movimiento_destinatario) {
		this.ultimo_movimiento_destinatario = ultimo_movimiento_destinatario;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_error_envio")
	public String getUltimo_movimiento_error_envio() {
		return ultimo_movimiento_error_envio;
	}

	public void setUltimo_movimiento_error_envio(String ultimo_movimiento_error_envio) {
		this.ultimo_movimiento_error_envio = ultimo_movimiento_error_envio;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_estado")
	public String getUltimo_movimiento_estado() {
		return ultimo_movimiento_estado;
	}

	public void setUltimo_movimiento_estado(String ultimo_movimiento_estado) {
		this.ultimo_movimiento_estado = ultimo_movimiento_estado;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_estado_string")
	public String getUltimo_movimiento_estado_string() {
		return ultimo_movimiento_estado_string;
	}

	public void setUltimo_movimiento_estado_string(String ultimo_movimiento_estado_string) {
		this.ultimo_movimiento_estado_string = ultimo_movimiento_estado_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_envio_integer")
	public Long getUltimo_movimiento_fecha_envio_integer() {
		return ultimo_movimiento_fecha_envio_integer;
	}

	public void setUltimo_movimiento_fecha_envio_integer(Long ultimo_movimiento_fecha_envio_integer) {
		this.ultimo_movimiento_fecha_envio_integer = ultimo_movimiento_fecha_envio_integer;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_envio_string")
	public String getUltimo_movimiento_fecha_envio_string() {
		return ultimo_movimiento_fecha_envio_string;
	}

	public void setUltimo_movimiento_fecha_envio_string(String ultimo_movimiento_fecha_envio_string) {
		this.ultimo_movimiento_fecha_envio_string = ultimo_movimiento_fecha_envio_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_hora_envio_string")
	public String getUltimo_movimiento_fecha_hora_envio_string() {
		return ultimo_movimiento_fecha_hora_envio_string;
	}

	public void setUltimo_movimiento_fecha_hora_envio_string(String ultimo_movimiento_fecha_hora_envio_string) {
		this.ultimo_movimiento_fecha_hora_envio_string = ultimo_movimiento_fecha_hora_envio_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_lectura_integer")
	public Long getUltimo_movimiento_fecha_lectura_integer() {
		return ultimo_movimiento_fecha_lectura_integer;
	}

	public void setUltimo_movimiento_fecha_lectura_integer(Long ultimo_movimiento_fecha_lectura_integer) {
		this.ultimo_movimiento_fecha_lectura_integer = ultimo_movimiento_fecha_lectura_integer;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_lectura_string")
	public String getUltimo_movimiento_fecha_lectura_string() {
		return ultimo_movimiento_fecha_lectura_string;
	}

	public void setUltimo_movimiento_fecha_lectura_string(String ultimo_movimiento_fecha_lectura_string) {
		this.ultimo_movimiento_fecha_lectura_string = ultimo_movimiento_fecha_lectura_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_queja_integer")
	public Long getUltimo_movimiento_fecha_queja_integer() {
		return ultimo_movimiento_fecha_queja_integer;
	}

	public void setUltimo_movimiento_fecha_queja_integer(Long ultimo_movimiento_fecha_queja_integer) {
		this.ultimo_movimiento_fecha_queja_integer = ultimo_movimiento_fecha_queja_integer;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_queja_string")
	public String getUltimo_movimiento_fecha_queja_string() {
		return ultimo_movimiento_fecha_queja_string;
	}

	public void setUltimo_movimiento_fecha_queja_string(String ultimo_movimiento_fecha_queja_string) {
		this.ultimo_movimiento_fecha_queja_string = ultimo_movimiento_fecha_queja_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_rebote_integer")
	public Long getUltimo_movimiento_fecha_rebote_integer() {
		return ultimo_movimiento_fecha_rebote_integer;
	}

	public void setUltimo_movimiento_fecha_rebote_integer(Long ultimo_movimiento_fecha_rebote_integer) {
		this.ultimo_movimiento_fecha_rebote_integer = ultimo_movimiento_fecha_rebote_integer;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_rebote_string")
	public String getUltimo_movimiento_fecha_rebote_string() {
		return ultimo_movimiento_fecha_rebote_string;
	}

	public void setUltimo_movimiento_fecha_rebote_string(String ultimo_movimiento_fecha_rebote_string) {
		this.ultimo_movimiento_fecha_rebote_string = ultimo_movimiento_fecha_rebote_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_recepcion_integer")
	public Long getUltimo_movimiento_fecha_recepcion_integer() {
		return ultimo_movimiento_fecha_recepcion_integer;
	}

	public void setUltimo_movimiento_fecha_recepcion_integer(Long ultimo_movimiento_fecha_recepcion_integer) {
		this.ultimo_movimiento_fecha_recepcion_integer = ultimo_movimiento_fecha_recepcion_integer;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_fecha_recepcion_string")
	public String getUltimo_movimiento_fecha_recepcion_string() {
		return ultimo_movimiento_fecha_recepcion_string;
	}

	public void setUltimo_movimiento_fecha_recepcion_string(String ultimo_movimiento_fecha_recepcion_string) {
		this.ultimo_movimiento_fecha_recepcion_string = ultimo_movimiento_fecha_recepcion_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_id")
	public String getUltimo_movimiento_id() {
		return ultimo_movimiento_id;
	}

	public void setUltimo_movimiento_id(String ultimo_movimiento_id) {
		this.ultimo_movimiento_id = ultimo_movimiento_id;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_motivo_estado")
	public String getUltimo_movimiento_motivo_estado() {
		return ultimo_movimiento_motivo_estado;
	}

	public void setUltimo_movimiento_motivo_estado(String ultimo_movimiento_motivo_estado) {
		this.ultimo_movimiento_motivo_estado = ultimo_movimiento_motivo_estado;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_motivo_estado_string")
	public String getUltimo_movimiento_motivo_estado_string() {
		return ultimo_movimiento_motivo_estado_string;
	}

	public void setUltimo_movimiento_motivo_estado_string(String ultimo_movimiento_motivo_estado_string) {
		this.ultimo_movimiento_motivo_estado_string = ultimo_movimiento_motivo_estado_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_nro_cargo_generado")
	public String getUltimo_movimiento_nro_cargo_generado() {
		return ultimo_movimiento_nro_cargo_generado;
	}

	public void setUltimo_movimiento_nro_cargo_generado(String ultimo_movimiento_nro_cargo_generado) {
		this.ultimo_movimiento_nro_cargo_generado = ultimo_movimiento_nro_cargo_generado;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_periodo")
	public String getUltimo_movimiento_periodo() {
		return ultimo_movimiento_periodo;
	}

	public void setUltimo_movimiento_periodo(String ultimo_movimiento_periodo) {
		this.ultimo_movimiento_periodo = ultimo_movimiento_periodo;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_remitente")
	public String getUltimo_movimiento_remitente() {
		return ultimo_movimiento_remitente;
	}

	public void setUltimo_movimiento_remitente(String ultimo_movimiento_remitente) {
		this.ultimo_movimiento_remitente = ultimo_movimiento_remitente;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_ses_message_id")
	public String getUltimo_movimiento_ses_message_id() {
		return ultimo_movimiento_ses_message_id;
	}

	public void setUltimo_movimiento_ses_message_id(String ultimo_movimiento_ses_message_id) {
		this.ultimo_movimiento_ses_message_id = ultimo_movimiento_ses_message_id;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_tipo")
	public String getUltimo_movimiento_tipo() {
		return ultimo_movimiento_tipo;
	}

	public void setUltimo_movimiento_tipo(String ultimo_movimiento_tipo) {
		this.ultimo_movimiento_tipo = ultimo_movimiento_tipo;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_tipo_string")
	public String getUltimo_movimiento_tipo_string() {
		return ultimo_movimiento_tipo_string;
	}

	public void setUltimo_movimiento_tipo_string(String ultimo_movimiento_tipo_string) {
		this.ultimo_movimiento_tipo_string = ultimo_movimiento_tipo_string;
	}

	@DynamoDBAttribute(attributeName = "ultimo_movimiento_usuario")
	public String getUltimo_movimiento_usuario() {
		return ultimo_movimiento_usuario;
	}

	public void setUltimo_movimiento_usuario(String ultimo_movimiento_usuario) {
		this.ultimo_movimiento_usuario = ultimo_movimiento_usuario;
	}

	@DynamoDBAttribute(attributeName = "ultimo_reclamo")
	public String getUltimo_reclamo() {
		return ultimo_reclamo;
	}

	public void setUltimo_reclamo(String ultimo_reclamo) {
		this.ultimo_reclamo = ultimo_reclamo;
	}

	@DynamoDBAttribute(attributeName = "visualizaciones")
	public List<String> getVisualizaciones() {
		return visualizaciones;
	}

	public void setVisualizaciones(List<String> visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	@Override
	public String toString() {
		return this.metadata.getNumeroDNICliente() + "|" + this.metadata.getTarjetaCreditoCliente() + "|" + periodo.toString();
	}

	

}
