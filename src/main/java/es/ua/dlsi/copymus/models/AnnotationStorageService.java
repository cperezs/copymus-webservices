package es.ua.dlsi.copymus.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.ua.dlsi.copymus.AppProperties;

@Service
public class AnnotationStorageService {
	
	Logger log = LoggerFactory.getLogger(AnnotationStorageService.class);
	
	@Autowired
	AppProperties conf;
	
	private void saveFile(Score score, User user, MultipartFile file, String filename) throws IllegalStateException, IOException {
		Path path = Paths.get(conf.getAnnotationsPath());
		path = path.resolve(score.getDb() + File.separator + score.getId() + File.separator + user.getId());
		path.toFile().mkdirs();
		File destination = path.resolve(filename).toFile();
		file.transferTo(destination);
		log.debug("File " + file.getOriginalFilename() + " copied to " + destination.getAbsolutePath());
	}
	
	public void saveImage(Score score, User user, MultipartFile file) throws IllegalStateException, IOException {
		saveFile(score, user, file, "image.png");
	}
	
	public void saveInteractions(Score score, User user, MultipartFile file) throws IllegalStateException, IOException {
		saveFile(score, user, file, "interactions.zip");
	}
}
