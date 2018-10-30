package es.ua.dlsi.copymus.dto.assemblers;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ua.dlsi.copymus.AppProperties;
import es.ua.dlsi.copymus.dto.AnnotationDto;
import es.ua.dlsi.copymus.models.Annotation;
import es.ua.dlsi.copymus.models.AnnotationStorageService;

@Component
public class AnnotationAssembler {
	
	@Autowired
	AppProperties conf;
	
	@Autowired
	AnnotationStorageService storageService;
	
	private String encodeToBase64(byte[] data) {
		return new String(Base64.getEncoder().encode(data));
	}
	
	public AnnotationDto getAnnotationDto(Annotation annotation) throws Exception {
		Logger log = LoggerFactory.getLogger(AnnotationAssembler.class);
		
		AnnotationDto dto = new AnnotationDto();
		String scoreId = annotation.getAnnotationIdentity().getScoreId();
		
		dto.setScoreId(scoreId);
		dto.setUserId(annotation.getAnnotationIdentity().getUserId());

		try {
			File imageFile = storageService.getImage(annotation);
			dto.setImage(encodeToBase64(Files.readAllBytes(imageFile.toPath())));
		} catch (Exception e) {
			log.error("Could not get image file for score [" + scoreId + "]: " + e.toString());
			throw e;
		}

		try {
			File interactionsFile = storageService.getInteractions(annotation);
			dto.setInteractions(encodeToBase64(Files.readAllBytes(interactionsFile.toPath())));
		} catch (Exception e) {
			log.error("Could not get interactions file for score [" + scoreId + "]: " + e.toString());
			throw e;
		}

		return dto;
	}
}
