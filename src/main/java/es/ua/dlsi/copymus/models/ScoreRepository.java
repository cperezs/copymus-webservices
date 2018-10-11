package es.ua.dlsi.copymus.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, String> {
	@Query(value="SELECT * FROM score ORDER BY RAND() LIMIT 1", nativeQuery=true)
	public Score getRandomScore();
}
