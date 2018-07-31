package es.ua.dlsi.copymus.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Annotation {
	// https://www.callicoder.com/hibernate-spring-boot-jpa-composite-primary-key-example/
	@Id
	private AnnotationIdentity annotationIdentity;
	
	public Annotation(AnnotationIdentity annotationIdentity) {
		this.annotationIdentity = annotationIdentity;
	}
	
	@Override
	public String toString() {
		return "Annotation [user_id=" + annotationIdentity.getUserId() + ", score_id=" + annotationIdentity.getScoreId() + "]";
	}
}
