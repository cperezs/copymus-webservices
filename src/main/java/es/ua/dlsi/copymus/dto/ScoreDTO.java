package es.ua.dlsi.copymus.dto;
import es.ua.dlsi.copymus.models.Score;

public class ScoreDTO {
	
	private String id;
	private String title;
	private String author;
	
	public ScoreDTO() {
		
	}
	
	public static ScoreDTO fromScore(Score score) {
		ScoreDTO dto = new ScoreDTO();
		dto.id = score.getId();
		dto.title = score.getTitle();
		dto.author = score.getAuthor();
		
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
