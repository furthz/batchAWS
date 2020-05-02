package pe.soapros.util;

public enum EstadoEnvio {

	SIN_MOVIMIENTO(0, "Sin Movimiento"),

	REZAGADO(1, "Rezagado"),

	REBOTE(2, "Rebote"),

	LEIDO(3, "Leído"),

	ENVIADO(4, "Enviado"),

	ENTREGADO(5, "Entregado");
	;

	private Integer code;

	private String message;

	private EstadoEnvio(final Integer paramCode, final String paramMessage) {
		this.code = paramCode;
		this.message = paramMessage;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
}
