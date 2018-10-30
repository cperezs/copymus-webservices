package es.ua.dlsi.copymus.controllers;

public class NotFoundException extends Exception implements CustomException {
	
	private static final long serialVersionUID = -8347890018796273658L;
	private String customMessage;
	
	public NotFoundException(String message) {
		customMessage = message;
	}

	@Override
	public String getCustomMessage() {
		return customMessage;
	}

}
