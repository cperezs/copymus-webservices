package es.ua.dlsi.copymus.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnnotationRepository extends CrudRepository<Annotation, AnnotationIdentity> {
	public List<AnnotationSummary> getByUserId(Long userId);
}
