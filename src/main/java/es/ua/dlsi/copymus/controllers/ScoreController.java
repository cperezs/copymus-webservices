package es.ua.dlsi.copymus.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ua.dlsi.copymus.dto.ScoreDto;
import es.ua.dlsi.copymus.dto.assemblers.ScoreAssembler;
import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.models.ScoreRepository;
import es.ua.dlsi.copymus.models.User;
import es.ua.dlsi.copymus.models.UserRepository;

@RestController
@RequestMapping("/score")
public class ScoreController {
/*	private class ScoreNotFoundException extends Exception {
		private static final long serialVersionUID = 169828852424309034L;		
	}
*/	
	private final Logger log = LoggerFactory.getLogger(ScoreController.class);
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/{db}/{scoreId}")
	public ResponseEntity<ScoreDto> getScore(@PathVariable("db") String db, @PathVariable("scoreId") String scoreId) {
		Optional<Score> score = scoreRepository.findByDbAndId(db, scoreId);
		if (!score.isPresent()) {
			log.info("Score " + scoreId + " not found in database " + db);
			return ResponseEntity.notFound().build();
		}
		
		try {
			ScoreDto dto = ScoreAssembler.getScoreDto(score.get());
			return ResponseEntity.ok(dto);
		}
		catch (Exception e) {
			log.error("An error occurred while creating a representation for score " + score.get().getId());
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{db}/{userId}/pending")
	public ResponseEntity<ScoreDto> getNotAnnotatedScoreForUser(@PathVariable("db") String db, @PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			log.info("User " + userId + " not found");
			return ResponseEntity.notFound().build();
		}
		
		Optional<Score> score = scoreRepository.getRandomScore(db);
		if (!score.isPresent()) {
			log.error("Could not find a random score");
			return ResponseEntity.notFound().build();
		}
			
		try {
			ScoreDto dto = ScoreAssembler.getScoreDto(score.get());
			return ResponseEntity.ok(dto);
		}
		catch (Exception e) {
			log.error("An error occurred while creating a representation for score " + score.get().getId());
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
