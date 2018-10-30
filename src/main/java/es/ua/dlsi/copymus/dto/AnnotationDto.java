package es.ua.dlsi.copymus.dto;

public class AnnotationDto {
	
	private String scoreId;
	private Long userId;
	private String image;
	private String interactions;
	
	public AnnotationDto() {
		
	}

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInteractions() {
		return interactions;
	}

	public void setInteractions(String interactions) {
		this.interactions = interactions;
	}

}
