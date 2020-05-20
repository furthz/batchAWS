package pe.soapros.services;

import java.util.Map;

import pe.soapros.exception.ConcurrencyException;

public interface Updating {

	void createParallelProcess(Map<String, Object> parameters) throws ConcurrencyException;
	
}
