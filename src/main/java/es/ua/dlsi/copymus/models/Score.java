package es.ua.dlsi.copymus.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	private String id;
	private String db;
	private String title;
	private String author;
	private String path;
	private boolean invalid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", db=" + db + ", title=" + title + ", author=" + author + ", path=" + path + ", invalid=" + invalid + "]";
	}
}
