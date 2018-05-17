package es.ua.dlsi.grfia.copymus.dbimport.primus;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import es.ua.dlsi.grfia.copymus.models.Score;
import es.ua.dlsi.grfia.copymus.models.ScoreRepository;

public class ScoreItemProcessor implements ItemProcessor<Score, Score> {

    private static final Logger log = LoggerFactory.getLogger(ScoreItemProcessor.class);
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    @Override
    public Score process(final Score score) throws Exception {
        Path pdf = Paths.get(score.getPdf());

        Score processedScore = new Score();
        Optional<Score> found = scoreRepository.findByPdf(score.getPdf());
        if (found.isPresent()) {
        	processedScore = found.get();
        }
        else {
            processedScore.setPdf(pdf.toString());
        }
        
        log.info("Processing " + processedScore);        
        
        String dir = pdf.getParent().toString();
        String midiFile = pdf.getFileName().toString().replaceAll(".pdf", ".mid");
        Path midi = Paths.get(dir, midiFile);        
        
        processedScore.setMidi(midi.toString());
        processedScore.setDb("primus");
        processedScore.setTitle("");
        processedScore.setAuthor("");
        
        return processedScore;
    }

}
