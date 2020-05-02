package pe.soapros.bean;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "capacniam-producto_movimiento-prd")
public class ProductoMovimiento {

	private String id;

	private Integer periodo;

	private String audit_fecha_creacion;
	private String audit_fecha_modificacion;
	private String audit_usuario_creacion;
	private String audit_usuario_modificacion;
	private String canal_movimiento;
	private String data;
	private List<String> destinatario;
	private String error;
	private String estado_movimiento;
	private String estado_movimiento_string;
	private String fecha_integer;
	private Long fecha_lectura_integer;
	private String fecha_lectura_string;
	private Long fecha_recepcion_integer;
	private String fecha_recepcion_string;
	private String fecha_string;

	private String metadata;
	private String nro_cargo_generado;

	private String producto_id;
	private String ses_message_id;
	private String tipo_movimiento;
	private String usuario;

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

	@DynamoDBAttribute(attributeName = "canal_movimiento")
	public String getCanal_movimiento() {
		return canal_movimiento;
	}

	public void setCanal_movimiento(String canal_movimiento) {
		this.canal_movimiento = canal_movimiento;
	}

	@DynamoDBAttribute(attributeName = "data")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@DynamoDBAttribute(attributeName = "destinatario")
	public List<String> getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(List<String> destinatario) {
		this.destinatario = destinatario;
	}

	@DynamoDBAttribute(attributeName = "error")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@DynamoDBAttribute(attributeName = "estado_movimiento")
	public String getEstado_movimiento() {
		return estado_movimiento;
	}

	public void setEstado_movimiento(String estado_movimiento) {
		this.estado_movimiento = estado_movimiento;
	}

	@DynamoDBAttribute(attributeName = "estado_movimiento_string")
	public String getEstado_movimiento_string() {
		return estado_movimiento_string;
	}

	public void setEstado_movimiento_string(String estado_movimiento_string) {
		this.estado_movimiento_string = estado_movimiento_string;
	}

	@DynamoDBAttribute(attributeName = "fecha_integer")
	public String getFecha_integer() {
		return fecha_integer;
	}

	public void setFecha_integer(String fecha_integer) {
		this.fecha_integer = fecha_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_lectura_integer")
	public Long getFecha_lectura_integer() {
		return fecha_lectura_integer;
	}

	public void setFecha_lectura_integer(Long fecha_lectura_integer) {
		this.fecha_lectura_integer = fecha_lectura_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_lectura_string")
	public String getFecha_lectura_string() {
		return fecha_lectura_string;
	}

	public void setFecha_lectura_string(String fecha_lectura_string) {
		this.fecha_lectura_string = fecha_lectura_string;
	}

	@DynamoDBAttribute(attributeName = "fecha_recepcion_integer")
	public Long getFecha_recepcion_integer() {
		return fecha_recepcion_integer;
	}

	public void setFecha_recepcion_integer(Long fecha_recepcion_integer) {
		this.fecha_recepcion_integer = fecha_recepcion_integer;
	}

	@DynamoDBAttribute(attributeName = "fecha_recepcion_string")
	public String getFecha_recepcion_string() {
		return fecha_recepcion_string;
	}

	public void setFecha_recepcion_string(String fecha_recepcion_string) {
		this.fecha_recepcion_string = fecha_recepcion_string;
	}

	@DynamoDBAttribute(attributeName = "fecha_string")
	public String getFecha_string() {
		return fecha_string;
	}

	public void setFecha_string(String fecha_string) {
		this.fecha_string = fecha_string;
	}

	@DynamoDBAttribute(attributeName = "metadata")
	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	@DynamoDBAttribute(attributeName = "nro_cargo_generado")
	public String getNro_cargo_generado() {
		return nro_cargo_generado;
	}

	public void setNro_cargo_generado(String nro_cargo_generado) {
		this.nro_cargo_generado = nro_cargo_generado;
	}

	@DynamoDBAttribute(attributeName = "producto_id")
	public String getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(String producto_id) {
		this.producto_id = producto_id;
	}

	@DynamoDBAttribute(attributeName = "ses_message_id")
	public String getSes_message_id() {
		return ses_message_id;
	}

	public void setSes_message_id(String ses_message_id) {
		this.ses_message_id = ses_message_id;
	}

	@DynamoDBAttribute(attributeName = "tipo_movimiento")
	public String getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

	@DynamoDBAttribute(attributeName = "usuario")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "ProductoMovimiento [id=" + id + ", periodo=" + periodo + ", audit_fecha_creacion="
				+ audit_fecha_creacion + ", audit_fecha_modificacion=" + audit_fecha_modificacion
				+ ", audit_usuario_creacion=" + audit_usuario_creacion + ", audit_usuario_modificacion="
				+ audit_usuario_modificacion + ", canal_movimiento=" + canal_movimiento + ", data=" + data
				+ ", destinatario=" + destinatario + ", error=" + error + ", estado_movimiento=" + estado_movimiento
				+ ", estado_movimiento_string=" + estado_movimiento_string + ", fecha_integer=" + fecha_integer
				+ ", fecha_lectura_integer=" + fecha_lectura_integer + ", fecha_lectura_string=" + fecha_lectura_string
				+ ", fecha_recepcion_integer=" + fecha_recepcion_integer + ", fecha_recepcion_string="
				+ fecha_recepcion_string + ", fecha_string=" + fecha_string + ", metadata=" + metadata
				+ ", nro_cargo_generado=" + nro_cargo_generado + ", producto_id=" + producto_id + ", ses_message_id="
				+ ses_message_id + ", tipo_movimiento=" + tipo_movimiento + ", usuario=" + usuario + "]";
	}
	
	

}
