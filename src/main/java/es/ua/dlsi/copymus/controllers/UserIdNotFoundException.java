package es.ua.dlsi.copymus.controllers;

public class UserIdNotFoundException extends Exception implements CustomException {

	private static final long serialVersionUID = -5605770477933417800L;
	Long id;
	
	public UserIdNotFoundException(Long id) {
		this.id = id;
	}

	public String getCustomMessage() {
		return "User with id [" + id + "] not found";
	}
		
}
