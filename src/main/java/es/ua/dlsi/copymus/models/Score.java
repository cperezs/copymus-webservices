package es.ua.dlsi.copymus.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Score {
	@Id
	private String id;
	private String db;
	private String title;
	private String author;
	private String path;
	private Set<User> annotators;

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
	
	@ManyToMany(mappedBy = "scores")
	public Set<User> getAnnotators() {
		return annotators;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", title=" + title + ", author=" + author + ", pdf=" + path + "]";
	}
}
