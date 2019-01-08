package es.ua.dlsi.copymus.models;

import org.springframework.beans.factory.annotation.Value;

public interface AnnotationSummary {
	@Value("#{target.annotationIdentity.scoreId}")
	public String getScoreId();
	
	@Value("#{target.score.db}")
	public String getDb();
	
	@Value("#{target.annotationIdentity.userId}")
	public Long getUserId();
	
	@Value("/scores/#{target.score.db}/#{target.annotationIdentity.scoreId}/#{target.annotationIdentity.userId}")
	public String getUrl();
	
	@Value("#{target.score.title}")
	public String getTitle();
	
	@Value("#{target.score.author}")
	public String getAuthor();
}
