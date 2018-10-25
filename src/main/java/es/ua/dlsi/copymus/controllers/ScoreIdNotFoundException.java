package es.ua.dlsi.copymus.controllers;

public class ScoreIdNotFoundException extends Exception implements CustomException {

	private static final long serialVersionUID = -1626637390597687477L;
	
	private String scoreId;
	
	public ScoreIdNotFoundException(String scoreId) {
		this.scoreId = scoreId;
	}

	public String getCustomMessage() {
		return "Score with id [" + scoreId + "] not found";
	}

}
