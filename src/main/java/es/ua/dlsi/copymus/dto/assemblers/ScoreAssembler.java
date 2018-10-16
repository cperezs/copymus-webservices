package es.ua.dlsi.copymus.dto.assemblers;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ua.dlsi.copymus.dto.ScoreDto;
import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.util.SVGUtils;

public class ScoreAssembler {
	
	private static String encodeToBase64(byte[] data) {
		return new String(Base64.getEncoder().encode(data));
	}
	
	public static ScoreDto getScoreDto(Score score) throws Exception {
		Logger log = LoggerFactory.getLogger(ScoreAssembler.class);
		
		ScoreDto dto = new ScoreDto();
		String id = score.getId();
		
		dto.setId(id);
		dto.setTitle(score.getTitle());
		dto.setAuthor(score.getAuthor());
		
		String svgPath = score.getPath() + File.separator + id + ".svg";
		try {
			dto.setPdf(encodeToBase64(SVGUtils.svg2pdf(svgPath)));
		} catch (Exception e) {
			log.error("Could not get PDF for score " + score.getId() + ": " + e.toString());
		}
		
		File midiFile = new File(score.getPath() + File.separator + id + ".mid");
		dto.setMidi(encodeToBase64(Files.readAllBytes(midiFile.toPath())));

		return dto;
	}
}
