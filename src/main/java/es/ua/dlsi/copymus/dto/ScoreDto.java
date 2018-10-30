package es.ua.dlsi.copymus.dto;

public class ScoreDto {
	
	private String id;
	private String title;
	private String author;
	private String pdf;
	private String midi;
	
	public ScoreDto() {
		
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

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getMidi() {
		return midi;
	}

	public void setMidi(String midi) {
		this.midi = midi;
	}

}
