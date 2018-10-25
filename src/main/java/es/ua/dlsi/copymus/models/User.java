package es.ua.dlsi.copymus.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;

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
	
	@Override
	public String toString() {
		String result = String.format(
				"User[id=%d, username='%s']%n",
				id, username);
//		if (scores != null) {
//			for (Score score : scores) {
//				result += String.format("Score[id=%s, name='%s']%n",
//						score.getId(), score.getTitle());
//			}
//		}
		
		return result;
	}
}
