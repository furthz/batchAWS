package pe.soapros.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import pe.soapros.bean.Contrato;
import pe.soapros.bean.Email;
import pe.soapros.bean.EmailMetadata;
import pe.soapros.bean.Producto;
import pe.soapros.bean.ProductoMetadata;
import pe.soapros.bean.ProductoMovimiento;
import pe.soapros.bean.Recurso;
import pe.soapros.bean.Reporte;

public class InformeUtil {

	private static final Logger log = Logger.getLogger(InformeUtil.class.getName());

	private static final String SEPARADOR = "\\|";

	private static final String CODING = "Utf8";

	private static final String DEFAULT = "none";
	
	public static Contrato loadLineContrato(final String line)throws Exception {
		Contrato cont = new Contrato();
		
		String[] elementos = line.split(";");
		
		if (elementos.length < 4) {
			throw new Exception("No tienen todos los datos esperados");
		}else {
			cont.setPan(elementos[0]);
			cont.setFec_corte(elementos[1]);
			cont.setId(elementos[2]);
			cont.setContrato("bn_ripley_" + elementos[3]);
		}
		
		return cont;
	}

	public static List<Reporte> loadReport(final S3ObjectInputStream inputStream) throws Exception {
		log.debug("Cargar el archivo de metadata");

		List<Reporte> rpta = new ArrayList<Reporte>();

		// FileInputStream inputStream = null;

		Scanner sc = null;

		try {
			// inputStream = new FileInputStream(input.getAbsolutePath());

			sc = new Scanner(inputStream, CODING);

			Reporte lnReporte = null;

			int cant = 1;
			// leer cada linea del archivo
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				if (cant > 1) {

					log.debug("Linea: " + line);

					lnReporte = convertLine(line);
					log.debug("Reporte: " + lnReporte.toString());

					rpta.add(lnReporte);
				}
				cant++;
			}

		} catch (Exception e) {
			log.error(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}

		return rpta;
	}

	private static Reporte convertLine(String line) {
		Reporte rpte = new Reporte();

		String[] elementos = line.split(SEPARADOR);

		String strDateFormat = "dd/MM/yyyy hh:mm:ss";
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		
		String strDateFormat2 = "MM/dd/yyyy hh:mm:ss";
		SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2);
		
		int cont = 0;

		try {

			// asignacion de los elementos
			try {
				rpte.setNomenclatura_pdf(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA NOM: " + e);
			}

			try {
				rpte.setDni(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA DNI: " + e);
			}

			try {
				rpte.setNum_tarjeta(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA TARJETA: " + e);
			}

			try {
				rpte.setCorte(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA CORTE: " + e);
			}

			try {
				rpte.setProducto(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA PRODUCTO: " + e);
			}

			try {
				rpte.setNombre_cliente(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA CLIENTE: " + e);
			}

			try {
				rpte.setTipo_envio(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA TIPOENVIO: " + e);
			}

			try {
				rpte.setCorreo_remitente(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA REMITENTE: " + e);
			}

			try {
				rpte.setCorreo_destinatario(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA DESTINATARIO: " + e);
			}

			try {
				rpte.setEstado_entrega(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA ESTADOENTREGA: " + e);
			}

			try {
				rpte.setDetalle_estado(elementos[cont++]);
			} catch (Exception e) {
				log.debug("FALTA DETALLE: " + e);
			}

			try {
				rpte.setFecha_hora_envio(objSDF2.parse(elementos[cont++]));
			} catch (Exception e) {
				log.debug("FALTA HORAENVIO: " + e);
			}

			try {
				rpte.setFecha_hora_entrega(objSDF2.parse(elementos[cont++]));
			} catch (Exception e) {
				log.debug("FALTA HORAENTREGA: " + e);
			}

			try {
				rpte.setFecha_hora_lectura_1(objSDF2.parse(elementos[cont++]));
			} catch (Exception e) {
				log.debug("FALTA FECHALECTURA1: " + e);
			}
			/*
			 * rpte.setFecha_hora_lectura_2(objSDF.parse(elementos[cont++]));
			 * rpte.setFecha_hora_lectura_3(objSDF.parse(elementos[cont++]));
			 * rpte.setLog_sustento(elementos[cont++]);
			 */
		} catch (Exception e) {
			log.debug("Campos faltantes: ", e);
		}

		return rpte;
	}

	public static boolean existeFileS3(AmazonS3 s3, String bucketName, String key) {
		boolean rpta = false;

		try {
			rpta = s3.doesObjectExist(bucketName, key);
		} catch (AmazonServiceException e2) {
			rpta = false;
			//log.error("No Exist File: " + key + "Error: " + e2);
		} catch (SdkClientException e1) {
			rpta = false;
			//log.error("No Exist File: " + key + "Error: " + e1);
		}

		return rpta;
	}

	public static Long getSizeFileS3(AmazonS3 s3, String bucketName, String key) {
		return s3.getObjectMetadata(bucketName, key).getContentLength();
	}

	public static void validateReport(Reporte report) throws Exception {

		if (report.getNomenclatura_pdf() == null || report.getNomenclatura_pdf().equals("")) {
			throw new Exception("No existe la NOMENCLATURA en el registro " + report);
		}

		// validaciones
		if (report.getDni() == null || report.getDni().equals("")) {
			throw new Exception("No existe el DNI en el registro " + report);
		}

		if (report.getNum_tarjeta() == null || report.getNum_tarjeta().equals("")) {
			throw new Exception("No existe el NUMTARJETA en el registro " + report);
		}

		if (report.getCorte() == null || report.getCorte().equals("")) {
			throw new Exception("No existe el CORTE en el registro " + report);
		}

		if (report.getProducto() == null || report.getProducto().equals("")) {
			throw new Exception("No existe el PRODUCTO en el registro " + report);
		}

		if (report.getNombre_cliente() == null || report.getNombre_cliente().equals("")) {
			throw new Exception("No existe el NOMCLIENTE en el registro " + report);
		}

	}

	public static Producto translateProducto(Reporte report, String uuid, boolean isFisico)
			throws NumberFormatException, ParseException, Exception {
		Producto producto = new Producto();

		try {

			Date objDate = new Date();
			String strDateFormat = "dd-MM-yyyy hh:mm:ss";
			SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

			// fecha de creacion actual
			producto.setAudit_fecha_creacion(objSDF.format(objDate));

			// audit_fecha_modificacion: null
			producto.setAudit_fecha_modificacion(DEFAULT);

			// "audit_usuario_creacion": null,//en duro
			producto.setAudit_usuario_creacion(DEFAULT);

			// "audit_usuario_modificacion": null,//en duro
			producto.setAudit_usuario_modificacion(DEFAULT);

			// "boolean_ultimo_movimiento_estado": "1",//en duro
			producto.setBoolean_ultimo_movimiento_estado("1");

			// "cantidad_reenvios": 1,//en duro
			producto.setCantidad_reenvios(1L);

			producto.setId("bn_ripley_" + report.getDni() + report.getNum_tarjeta());

			// "cliente_id": "bn_ripley",//en duro
			producto.setCliente_id("bn_ripley");

			// "custom01": "bn_ripley_96043-000048980-40",//bn_ripley_${pan}
			producto.setCustom01("bn_ripley_" + report.getNum_tarjeta());

			// "custom02": "bn_ripley_00010001000005987350",//bn_ripley_${contrato}
			producto.setCustom02("bn_ripley_historico");

			// "custom03": "bn_ripley_02818086",//bn_ripley_${dni}
			producto.setCustom03("bn_ripley_" + report.getDni());

			// email_metadata null
			EmailMetadata metadata = new EmailMetadata();
			metadata.setBody(DEFAULT);
			Email mail = new Email();

			mail.setCuota(DEFAULT);
			mail.setFechaPago(DEFAULT);
			mail.setFechaSaldo(DEFAULT);
			mail.setFechaVigencia(DEFAULT);
			mail.setHash(DEFAULT);
			mail.setMes(DEFAULT);
			mail.setMontoAPagar(DEFAULT);
			mail.setMontoEfectivoDisponible(DEFAULT);
			mail.setPagoMinimo(DEFAULT);
			mail.setPlazo(DEFAULT);
			mail.setPrimerNombre(DEFAULT);
			mail.setTcea(DEFAULT);
			mail.setUrl(DEFAULT);

			metadata.setHtml(mail);

			producto.setEmail_metadata(metadata);

			// "error_generacion": null,//en duro
			producto.setError_generacion(DEFAULT);

			// "fecha_corte_integer": 20200219,//fecha de corte integer formato YYYYMMDD
			producto.setFecha_corte_integer(Long.valueOf(report.getFechaCorteInteger()));

			// "fecha_corte_string": "19-02-2020",//fecha de corte string formato DD-MM-YYYY
			producto.setFecha_corte_string(report.getFechaCorte());

			// "fecha_entrega_integer": null,//fecha de entrega integer formato YYYYMMDD
			producto.setFecha_entrega_integer(0L);

			// "fecha_entrega_string": null,//fecha de entrega string formato DD-MM-YYYY
			producto.setFecha_entrega_string(DEFAULT);

			// "fecha_generacion_integer": 20200331175843,//fecha de generacion integer
			// formato YYYYMMDDHHmmss
			String strDateF = "yyyyMMddhhmmss";
			SimpleDateFormat objFormat = new SimpleDateFormat(strDateF);
			producto.setFecha_generacion_integer(Long.valueOf(objFormat.format(objDate)));

			// "fecha_generacion_string": "31-03-2020 17:58:43",//fecha de generacion string
			// formato DD-MM-YYYY HH:mm:ss
			producto.setFecha_generacion_string(objSDF.format(objDate));

			// "fecha_primer_envio_integer": 20200331,//fecha de primer envio integer
			// formato YYYYMMDD
			String strDateF1 = "yyyyMMdd";
			SimpleDateFormat objFormat1 = new SimpleDateFormat(strDateF1);
			producto.setFecha_primer_envio_integer(Long.valueOf(objFormat1.format(report.getFecha_hora_envio())));

			// "fecha_primer_envio_string": "31-03-2020",//fecha de primer envio string
			// formato DD-MM-YYYY
			String strDateFormat22 = "dd-MM-yyyy";
			SimpleDateFormat objSDF22 = new SimpleDateFormat(strDateFormat22);
			producto.setFecha_primer_envio_string(objSDF22.format(report.getFecha_hora_envio()));

			// "instancia_proceso_id": null,//en duro
			producto.setInstancia_proceso_id(DEFAULT);

			// "instancia_proceso_output_id": null,//en duro
			producto.setInstancia_proceso_output_id(DEFAULT);

			// "instancia_proceso_periodo": null,//en duro
			producto.setInstancia_proceso_periodo(DEFAULT);

			// "llave": null,//en duro, porque no hay envio de aws
			producto.setLlave(DEFAULT);

			ProductoMetadata meta = new ProductoMetadata();

			meta.setCorrelativo("1");

			meta.setFechaPago(DEFAULT);

			meta.setFechaPago_integer(DEFAULT);

			meta.setFechaSaldo(DEFAULT);

			// "fechaUltimoClickECI": null,//en duro
			meta.setFechaUltimoClickECI(DEFAULT);

			// "fechaVigencia": null,//dato del spool
			meta.setFechaVigencia(DEFAULT);

			// "marca_procesamiento_recepcion": "none",//en duro
			meta.setMarca_procesamiento_recepcion(DEFAULT);

			meta.setMontoAPagar(DEFAULT);

			meta.setNumeroContrato(DEFAULT);

			meta.setNumeroDNICliente(report.getDni());

			meta.setPagoMinimo(DEFAULT);

			meta.setPeso(DEFAULT);

			meta.setRazonSocialCliente(report.getNombre_cliente());

			meta.setSaldo(DEFAULT);

			meta.setTarjetaCreditoCliente(report.getNum_tarjeta());

			if (isFisico) {
				meta.setTipoEnvio("120");
			} else {
				meta.setTipoEnvio("112");
			}

			meta.setTipoTarjeta(report.getProducto());

			meta.setTotalPaginasEC("1");
			
			producto.setMetadata(meta);

			// "mimetype": "application/pdf",//en duro
			producto.setMimetype("application/pdf");

			// "periodo": 202002,//periodo YYYYMM asociado a la fecha de generacion
			producto.setPeriodo(Integer.valueOf(report.getPeriodo()));

			// "reclamos": [],//en duro
			producto.setReclamos(new ArrayList<String>());

			List<Recurso> lstRecursos = new ArrayList<Recurso>();

			Recurso rec1 = new Recurso();
			rec1.setCanal("11");
			rec1.setNombre("ec_con_password");
			rec1.setFecha_expiracion(DEFAULT);
			rec1.setPeso(report.getSize().toString());
			rec1.setUbicacion(report.getS3_url());

			lstRecursos.add(rec1);

			Recurso rec2 = new Recurso();
			rec2.setCanal("11");
			rec2.setNombre("ec_sin_password");
			rec2.setFecha_expiracion(DEFAULT);
			rec2.setPeso(report.getSize().toString());
			rec2.setUbicacion(report.getS3_url());

			lstRecursos.add(rec2);

			producto.setRecursos(lstRecursos);

			// "tipo_producto": "10",//en duro
			producto.setTipo_producto("10");

			// "tipo_producto_string": "Estado de Cuenta",//en duro
			producto.setTipo_producto_string("Estado de Cuenta");

			// "tipo_subproducto": "112",//110 sef 111 con efex, 112 sin efex
			if (isFisico) {
				producto.setTipo_subproducto("120");
				// "tipo_subproducto_string": "EECC Virtual TC Sin Oferta",//110 = EECC Virtual
				// TC SEF , 111 = EECC Virtual TC EFEX 112 = EECC Virtual TC Sin Oferta
				producto.setTipo_subproducto_string("EECC Fisico Simple");
			} else {
				producto.setTipo_subproducto("112");
				// "tipo_subproducto_string": "EECC Virtual TC Sin Oferta",//110 = EECC Virtual
				// TC SEF , 111 = EECC Virtual TC EFEX 112 = EECC Virtual TC Sin Oferta
				producto.setTipo_subproducto_string("EECC Virtual TC Sin Oferta");
			}

			List<String> destinatarios = new ArrayList<String>();
			destinatarios.add(report.getCorreo_destinatario());

			producto.setUltimo_movimiento_destinatario(destinatarios);

			// "ultimo_movimiento_error_envio": null,
			producto.setUltimo_movimiento_error_envio(DEFAULT);

			// "ultimo_movimiento_estado": "4",//4 Enviado, 3 Leido
			producto.setUltimo_movimiento_estado("4");

			// "ultimo_movimiento_estado_string": "Enviado",//4 Enviado, 3 Leido
			producto.setUltimo_movimiento_estado_string("Enviado");

			// "ultimo_movimiento_fecha_envio_integer": 20200331,//fecha de envio integer
			// formato YYYYMMDD
			String strDateFormat1 = "yyyyMMdd";
			SimpleDateFormat objSDF1 = new SimpleDateFormat(strDateFormat1);
			producto.setUltimo_movimiento_fecha_envio_integer(
					Long.valueOf(objSDF1.format(report.getFecha_hora_entrega()).toString()));

			// "ultimo_movimiento_fecha_envio_string": "31-03-2020",//fecha de envio string
			// formato DD-MM-YYYY
			String strDateFormat2 = "dd-MM-yyyy";
			SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2);
			producto.setUltimo_movimiento_fecha_envio_string(objSDF2.format(report.getFecha_hora_entrega()).toString());

			// "ultimo_movimiento_fecha_hora_envio_string": "31-03-2020 13:01:25",//fecha
			// envio string formato DD-MM-YYYY HH:mm:ss
			producto.setUltimo_movimiento_fecha_hora_envio_string(objSDF.format(report.getFecha_hora_entrega()));

			// "ultimo_movimiento_fecha_lectura_integer": null,//fecha lectura integer
			// formato YYYYMMDD
			producto.setUltimo_movimiento_fecha_lectura_integer(
					Long.valueOf(objSDF1.format(report.getFecha_hora_lectura_1()).toString()));

			// "ultimo_movimiento_fecha_lectura_string": null,//fecha lectura string formato
			// DD-MM-YYYY HH:mm:ss
			producto.setUltimo_movimiento_fecha_lectura_string(objSDF.format(report.getFecha_hora_lectura_1()));

			// ultimo_movimiento_fecha_queja_integer
			producto.setUltimo_movimiento_fecha_queja_integer(0L);

			producto.setUltimo_movimiento_fecha_queja_string(DEFAULT);

			producto.setUltimo_movimiento_fecha_rebote_integer(0L);

			producto.setUltimo_movimiento_fecha_rebote_string(DEFAULT);

			producto.setUltimo_movimiento_fecha_recepcion_integer(0L);

			producto.setUltimo_movimiento_fecha_recepcion_string(DEFAULT);

			// "ultimo_movimiento_id":
			// "ad5b388e-1640-4bd5-b35a-4a121c175bb9",//${id_producto_movimiento}
			producto.setUltimo_movimiento_id(uuid);

			producto.setUltimo_movimiento_motivo_estado(DEFAULT);

			producto.setUltimo_movimiento_motivo_estado_string(DEFAULT);

			producto.setUltimo_movimiento_nro_cargo_generado(DEFAULT);

			// "ultimo_movimiento_periodo": 202003,//${periodo_producto_movimiento}
			producto.setUltimo_movimiento_periodo(report.getPeriodo());

			// "ultimo_movimiento_remitente": "Zamiel-QA<zamiel@soapros.info>",//correo del
			// remitente
			producto.setUltimo_movimiento_remitente("Raul Talledo<raul.talledo@soapros.pe>");

			producto.setUltimo_movimiento_ses_message_id(DEFAULT);

			producto.setUltimo_movimiento_tipo(DEFAULT);

			producto.setUltimo_movimiento_tipo_string(DEFAULT);

			// "ultimo_movimiento_usuario": "SOA",//en duro
			producto.setUltimo_movimiento_usuario("SOA");

			producto.setUltimo_reclamo(DEFAULT);

			producto.setVisualizaciones(new ArrayList<String>());

		} catch (Exception e) {
			throw new Exception("Error Translate Producto", e);
		}
		return producto;
	}

	public static ProductoMovimiento translateProductoMovimiento(Reporte report, String uuid) throws Exception {
		ProductoMovimiento mov = new ProductoMovimiento();
		try {

			mov.setId(uuid);

			mov.setPeriodo(Integer.valueOf(report.getPeriodo()));

			mov.setProducto_id("bn_ripley_" + report.getDni() + "_" + report.getNum_tarjeta());

			mov.setTipo_movimiento("1");

			mov.setUsuario("SOA");

			Date objDate = new Date();
			String strDateFormat = "dd-MM-yyyy hh:mm:ss";
			SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

			// "audit_fecha_creacion": "25-03-2020 01:06:06",//fecha creacion DD-MM-YYYY
			// HH:mm:ss
			mov.setAudit_fecha_creacion(objSDF.format(objDate));

			// "audit_fecha_modificacion": "25-03-2020 08:52:05",//fecha modificacion,
			// opcional
			mov.setAudit_fecha_modificacion(objSDF.format(objDate));

			// "audit_usuario_creacion": null,//en duro
			mov.setAudit_usuario_creacion(DEFAULT);

			// "audit_usuario_modificacion": null,//en duro
			mov.setAudit_usuario_modificacion(DEFAULT);

			// "canal_movimiento": "11",//en duro
			mov.setCanal_movimiento("11");

			mov.setData("{}");

			List<String> destinatarios = new ArrayList<String>();
			destinatarios.add(report.getCorreo_destinatario());

			mov.setDestinatario(destinatarios);

			// "error": null,//en duro
			mov.setError(DEFAULT);

			// "estado_movimiento": "3",//4 Entregado, 3 leido
			// "estado_movimiento_string": "Leido",//4 Entregado 3 Leido

			if (report.getEstado_entrega().equals("En Cola")) {

				mov.setEstado_movimiento(EstadoEnvio.SIN_MOVIMIENTO.getCode().toString());
				mov.setEstado_movimiento_string(EstadoEnvio.SIN_MOVIMIENTO.getMessage());

			} else if (report.getEstado_entrega().equals("Enviado")) {

				mov.setEstado_movimiento(EstadoEnvio.ENVIADO.getCode().toString());
				mov.setEstado_movimiento_string(EstadoEnvio.ENVIADO.getMessage());

			} else if (report.getEstado_entrega().equals("Error")) {

				mov.setEstado_movimiento(EstadoEnvio.REBOTE.getCode().toString());
				mov.setEstado_movimiento_string(EstadoEnvio.REBOTE.getMessage());

			} else if (report.getEstado_entrega().equals("Leído")) {

				mov.setEstado_movimiento(EstadoEnvio.LEIDO.getCode().toString());
				mov.setEstado_movimiento_string(EstadoEnvio.LEIDO.getMessage());

			} else if (report.getEstado_entrega().equals("Rebote")) {

				mov.setEstado_movimiento(EstadoEnvio.REBOTE.getCode().toString());
				mov.setEstado_movimiento_string(EstadoEnvio.REBOTE.getMessage());

			} else {

				mov.setEstado_movimiento(EstadoEnvio.SIN_MOVIMIENTO.getCode().toString());
				mov.setEstado_movimiento_string(EstadoEnvio.SIN_MOVIMIENTO.getMessage());

			}

			String strDateFormat1 = "yyyyMMdd";
			SimpleDateFormat objSDF1 = new SimpleDateFormat(strDateFormat1);

			// "fecha_integer": 20200325,//fecha generacion integer formato YYYYMMDD
			mov.setFecha_integer(objSDF1.format(report.getFecha_hora_envio()).toString());

			// "fecha_lectura_integer": "20200325085135",//fecha lectura si aplica integer
			// formato YYYYMMDDHHmmss
			String strDateFormat2 = "yyyyMMddhhmmss";
			SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2);
			mov.setFecha_lectura_integer(Long.valueOf(objSDF2.format(report.getFecha_hora_envio()).toString()));

			// "fecha_lectura_string": "25-03-2020 08:51:35",//fecha lectura si aplica
			// string formato DD-MM-YYYY HH:mm:ss
			mov.setFecha_lectura_string(objSDF.format(report.getFecha_hora_lectura_1()));

			mov.setFecha_recepcion_integer(0L);

			mov.setFecha_recepcion_string(DEFAULT);

			mov.setFecha_string(objSDF.format(objDate));

			mov.setMetadata("{}");

			mov.setNro_cargo_generado(DEFAULT);

			mov.setSes_message_id(DEFAULT);

			mov.setTipo_movimiento("1");

			mov.setUsuario("SOA");

		} catch (Exception e) {
			throw new Exception("Error Translate Producto Movimiento", e);
		}
		return mov;
	}
}
