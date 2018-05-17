package es.ua.dlsi.grfia.copymus.dbimport.primus;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import es.ua.dlsi.grfia.copymus.models.Score;
import es.ua.dlsi.grfia.copymus.models.ScoreRepository;

public class ScoreItemWriter implements ItemWriter<Score> {

	private final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    private ScoreRepository scoreRepository;

	@Override
	public void write(List<? extends Score> scores) throws Exception {
		log.info("--- Writing scores ---");
		
		for (Score score : scores) {
			scoreRepository.save(score);
			log.info(score.toString());
		}
		
		log.info("--- Finished writing scores ---");
	}

}
