package es.ua.dlsi.copymus.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends CrudRepository<Score, String> {
	
	public Optional<Score> findByDbAndId(String db, String id);
	
	@Query(value="SELECT * FROM score WHERE db = :db ORDER BY RAND() LIMIT 1", nativeQuery=true)
	public Optional<Score> getRandomScore(@Param("db") String db);
}
