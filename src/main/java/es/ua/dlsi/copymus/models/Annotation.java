package es.ua.dlsi.copymus.models;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Annotation {
	// https://www.callicoder.com/hibernate-spring-boot-jpa-composite-primary-key-example/
	@EmbeddedId
	private AnnotationIdentity annotationIdentity;
	
	private Timestamp creationDate;
	
	@ManyToOne
	@JoinColumn(name = "score_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Score score;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	
	public Annotation() {
		initDate();
	}
	
	public Annotation(AnnotationIdentity annotationIdentity) {
		initDate();
		this.annotationIdentity = annotationIdentity;
	}
	
	private void initDate() {
		this.setCreationDate(new Timestamp(System.currentTimeMillis()));
	}
	
	@Override
	public String toString() {
		return "Annotation [user_id=" + annotationIdentity.getUserId() + ", score_id=" + annotationIdentity.getScoreId() + "]";
	}
	
	public AnnotationIdentity getAnnotationIdentity() {
		return annotationIdentity;
	}

	public void setAnnotationIdentity(AnnotationIdentity annotationIdentity) {
		this.annotationIdentity = annotationIdentity;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
