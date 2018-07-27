package es.ua.dlsi.grfia.copymus.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	private Long id;
	private String db;
	private String title;
	private String author;
	private String path;

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

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", title=" + title + ", author=" + author + ", pdf=" + path + "]";
	}
}
