package pe.soapros.bean;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class ProductoMetadataConvert implements DynamoDBTypeConverter<String, ProductoMetadata>{

	@Override
	public String convert(ProductoMetadata object) {
		ProductoMetadata prod = (ProductoMetadata) object;
		String producto = null;
		
		try {
			
			if(prod != null) {
				producto = String.format("%s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s", 
						                 prod.getCorrelativo(),
						                 prod.getFechaPago(),
						                 prod.getFechaPago_integer(),
						                 prod.getFechaSaldo(),
						                 prod.getFechaUltimoClickECI(),
						                 prod.getFechaVigencia(),
						                 prod.getMarca_procesamiento_recepcion(),
						                 prod.getMontoAPagar(),
						                 prod.getNumeroContrato(),
						                 prod.getNumeroDNICliente(),
						                 prod.getPagoMinimo(),
						                 prod.getPeso(),
						                 prod.getRazonSocialCliente(),
						                 prod.getSaldo(),
						                 prod.getTarjetaCreditoCliente(),
						                 prod.getTipoEnvio(),
						                 prod.getTipoTarjeta(),
						                 prod.getTotalPaginasEC());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return producto;
	}

	@Override
	public ProductoMetadata unconvert(String s) {
		ProductoMetadata prod = new ProductoMetadata();
		
		try {
			if(s != null && s.length() != 0) {
				String[] data = s.split("x");
				prod.setCorrelativo(data[0]);
				prod.setFechaPago(data[1]);
				prod.setFechaPago_integer(data[3]);
				prod.setFechaSaldo(data[4]);
				prod.setFechaUltimoClickECI(data[5]);
				prod.setFechaVigencia(data[6]);
				prod.setMarca_procesamiento_recepcion(data[7]);
				prod.setMontoAPagar(data[8]);
				prod.setNumeroContrato(data[9]);
				prod.setNumeroDNICliente(data[10]);
				prod.setPagoMinimo(data[11]);
				prod.setPeso(data[12]);
				prod.setRazonSocialCliente(data[13]);
				prod.setSaldo(data[14]);
				prod.setTarjetaCreditoCliente(data[15]);
				prod.setTipoEnvio(data[16]);
				prod.setTipoTarjeta(data[17]);
				prod.setTotalPaginasEC(data[18]);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return prod;
	}

}
