package es.ua.dlsi.copymus.controllers;

public class ErrorException extends Exception implements CustomException {
	
	private static final long serialVersionUID = 8353076894655752061L;
	private String customMessage;
	
	public ErrorException(String message) {
		customMessage = message;
	}

	@Override
	public String getCustomMessage() {
		return customMessage;
	}

}
