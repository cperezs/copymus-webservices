package es.ua.dlsi.copymus.dto.assemblers;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ua.dlsi.copymus.AppProperties;
import es.ua.dlsi.copymus.dto.ScoreDto;
import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.util.SVGUtils;

@Component
public class ScoreAssembler {
	
	@Autowired
	AppProperties conf;
	
	private String encodeToBase64(byte[] data) {
		return new String(Base64.getEncoder().encode(data));
	}
	
	public ScoreDto getScoreDto(Score score) throws Exception {
		Logger log = LoggerFactory.getLogger(ScoreAssembler.class);
		
		ScoreDto dto = new ScoreDto();
		String id = score.getId();
		
		dto.setId(id);
		dto.setTitle(score.getTitle());
		dto.setAuthor(score.getAuthor());
		
		String svgPath = conf.getDatabasesPath() + score.getPath() + File.separator + id + ".svg";
		try {
			dto.setPdf(encodeToBase64(SVGUtils.svg2pdf(svgPath)));
		} catch (Exception e) {
			log.error("Could not get PDF for score [" + score.getId() + "]: " + e.toString());
			throw e;
		}
		
		File midiFile = new File(conf.getDatabasesPath() + score.getPath() + File.separator + id + ".mid");
		try {
			dto.setMidi(encodeToBase64(Files.readAllBytes(midiFile.toPath())));
		} catch (Exception e) {
			log.error("Could not get MIDI file for score [" + score.getId() + "]: " + e.toString());
			throw e;
		}

		return dto;
	}
}
