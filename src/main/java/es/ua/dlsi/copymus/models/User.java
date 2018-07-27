package es.ua.dlsi.copymus.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private Set<Score> scores;

	protected User() {
	}

	public User(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "annotation", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "score_id", referencedColumnName = "id"))
	public Set<Score> getAnnotatedScores() {
		return scores;
	}
	
	public void setAnnotatedScores(Set<Score> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		String result = String.format(
				"User[id=%d, username='%s']%n",
				id, username);
		if (scores != null) {
			for (Score score : scores) {
				result += String.format("Score[id=%s, name='%s']%n",
						score.getId(), score.getTitle());
			}
		}
		
		return result;
	}
}
