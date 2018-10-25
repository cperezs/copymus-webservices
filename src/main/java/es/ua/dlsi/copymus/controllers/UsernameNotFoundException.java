package es.ua.dlsi.copymus.controllers;

public class UsernameNotFoundException extends Exception implements CustomException {

	private static final long serialVersionUID = 1154948207075183533L;

	String username;
	
	public UsernameNotFoundException(String username) {
		this.username = username;
	}

	public String getCustomMessage() {
		return "User with username [" + username + "] not found";
	}
		
}
