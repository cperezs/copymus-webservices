package es.ua.dlsi.grfia.copymus.models;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long> {
	public Optional<Score> findByPdf(String pdf);
}
