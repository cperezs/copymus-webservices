package es.ua.dlsi.copymus.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnnotationIdentity implements Serializable {
	private static final long serialVersionUID = -1915998982986005508L;
	
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "score_id")
	private String scoreId;
	
	public AnnotationIdentity() {}
	
	public AnnotationIdentity(Long userId, String scoreId) {
		this.userId = userId;
		this.scoreId = scoreId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String getScoreId() {
		return scoreId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		AnnotationIdentity that = (AnnotationIdentity)o;
		
		if (!userId.equals(that.userId)) return false;
		return scoreId.equals(that.scoreId);
	}
	
	@Override
	public int hashCode() {
		int result = userId.hashCode();
		result = 31 * result + scoreId.hashCode();
		return result;
	}
}
