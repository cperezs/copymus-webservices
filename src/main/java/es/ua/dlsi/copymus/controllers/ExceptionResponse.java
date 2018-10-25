package es.ua.dlsi.copymus.controllers;

public class ExceptionResponse {
	
	private String error;
	
	public ExceptionResponse(CustomException e) {
		error = e.getCustomMessage();
	}
	
	public String getError() {
		return error;
	}
}
