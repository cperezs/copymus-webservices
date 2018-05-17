package es.ua.dlsi.grfia.copymus.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String db;
	private String title;
	private String author;
	private String pdf;
	private String midi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
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

	@Override
	public String toString() {
		return "Score [id=" + id + ", title=" + title + ", author=" + author + ", pdf=" + pdf + ", midi=" + midi + "]";
	}
}
