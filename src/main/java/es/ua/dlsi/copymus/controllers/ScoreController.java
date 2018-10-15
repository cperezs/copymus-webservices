package es.ua.dlsi.copymus.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.ua.dlsi.copymus.dto.ScoreDto;
import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.models.ScoreRepository;
import es.ua.dlsi.copymus.models.User;
import es.ua.dlsi.copymus.models.UserRepository;

@RestController
@RequestMapping("/score")
public class ScoreController {
	private class ScoreNotFoundException extends Exception {
		private static final long serialVersionUID = 169828852424309034L;		
	}
	
	private final Logger log = LoggerFactory.getLogger(ScoreController.class);
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/{db}/{userId}/pending")
	public ResponseEntity<ScoreDto> getNotAnnotatedScoreForUser(@PathVariable("db") String db, @PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			return ResponseEntity.notFound().build();
		
		//Optional<Score> score = scoreRepository.getRandomScore(db);
		Optional<Score> score = scoreRepository.findById("211010353-1_3_1");
		if (!score.isPresent())
			return ResponseEntity.notFound().build();
			
		log.debug(score.get().toString());
		
		try {
			ScoreDto dto = ScoreDto.fromScore(score.get());
			return ResponseEntity.ok(dto);
		}
		catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
/*	@GetMapping(
			value = "/{id}.mid",
			produces = "audio/midi"
	)
	public @ResponseBody byte[] getMidi(@PathVariable("id") String scoreId) throws ScoreNotFoundException, IOException {
		Optional<Score> score = scoreRepository.findById(scoreId);
		if (!score.isPresent())
			throw new ScoreNotFoundException();
		
		File file = new File(score.get().getPath() + File.separator + scoreId + ".mid");
		log.trace(file.getAbsolutePath() + " exists: " + file.exists());
		return Files.readAllBytes(file.toPath());
	}
	
	@ExceptionHandler({ScoreNotFoundException.class})
	public ResponseEntity<String> handleScoreNotFoundException() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Score not found");
	}
	
	@ExceptionHandler({IOException.class})
	public ResponseEntity<String> handleIOException() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
	}
*/
}
