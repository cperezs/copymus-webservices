package es.ua.dlsi.copymus.dto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import es.ua.dlsi.copymus.models.Score;

public class ScoreDto {
	
	private String id;
	private String title;
	private String author;
	private String pdf;
	private String midi;
	
	public ScoreDto() {
		
	}
	
	public static ScoreDto fromScore(Score score) throws IOException {
		ScoreDto dto = new ScoreDto();
		String id = score.getId();
		
		dto.id = id;
		dto.title = score.getTitle();
		dto.author = score.getAuthor();
		dto.pdf = "/score/" + id + ".pdf";
		dto.midi = encodeFileToBase64Binary(score.getPath() + File.separator + id + ".mid");
		
		return dto;
	}

	private static String encodeFileToBase64Binary(String fileName)
			throws IOException {

		File file = new File(fileName);
		return new String(Base64.getEncoder().encode(Files.readAllBytes(file.toPath())));
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
