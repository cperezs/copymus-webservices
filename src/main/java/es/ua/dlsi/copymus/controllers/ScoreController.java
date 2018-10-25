package es.ua.dlsi.copymus.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.ua.dlsi.copymus.dto.ScoreDto;
import es.ua.dlsi.copymus.dto.assemblers.ScoreAssembler;
import es.ua.dlsi.copymus.models.Annotation;
import es.ua.dlsi.copymus.models.AnnotationIdentity;
import es.ua.dlsi.copymus.models.AnnotationRepository;
import es.ua.dlsi.copymus.models.AnnotationStorageService;
import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.models.ScoreRepository;
import es.ua.dlsi.copymus.models.User;
import es.ua.dlsi.copymus.models.UserRepository;

@RestController
@RequestMapping("/score")
public class ScoreController {

	private final Logger log = LoggerFactory.getLogger(ScoreController.class);
	
	private static final String SCORE_ID_NOT_FOUND = "Score with id [%s] not found";
	private static final String USERNAME_NOT_FOUND = "User with username [%s] not found";
	private static final String USER_ID_NOT_FOUND = "User with id [%d] not found";
	private static final String RANDOM_SCORE_ERROR = "Could not find a random score";
	private static final String REPRESENTATION_ERROR = "An error occurred while creating a representation for score [%s]";
	private static final String SAVE_ANNOTATION_ERROR = "Error while saving annotation files for score [%s]";
	private static final String SCORE_REPRESENTATION_ERROR = "An error occurred while creating a representation for score [%s]";
	
	@Autowired
	ScoreAssembler scoreAssembler;
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AnnotationRepository annotationRepository;
	
	@Autowired
	AnnotationStorageService storageService;
	
	@GetMapping("/{db}/{scoreId}")
	@ResponseStatus(HttpStatus.OK)
	public ScoreDto getScore(@PathVariable("db") String db, @PathVariable("scoreId") String scoreId) throws NotFoundException, ErrorException {
		Optional<Score> score = scoreRepository.findByDbAndId(db, scoreId);
		if (!score.isPresent())
			throw new NotFoundException(String.format(SCORE_ID_NOT_FOUND, scoreId));
		
		try {
			return scoreAssembler.getScoreDto(score.get());
		}
		catch (Exception e) {
			throw new ErrorException(String.format(SCORE_REPRESENTATION_ERROR, scoreId));
		}
	}
	
	@PostMapping("/{db}/{scoreId}/annotate")
	@ResponseStatus(HttpStatus.CREATED)
	public void createAnnotation(@PathVariable("db") String db,
			@PathVariable("scoreId") String scoreId,
			@RequestParam("username") String username,
			@RequestParam("image") MultipartFile image,
			@RequestParam("interactions") MultipartFile interactions) throws ErrorException, NotFoundException {
		
		Optional<Score> score = scoreRepository.findByDbAndId(db, scoreId);
		if (!score.isPresent()) {
			throw new NotFoundException(String.format(SCORE_ID_NOT_FOUND, scoreId));
		}
		
		Optional<User> user = userRepository.findByUsernameIgnoreCase(username);
		if (!user.isPresent())
			throw new NotFoundException(String.format(USERNAME_NOT_FOUND, username));
		
		try {
			storageService.saveImage(score.get(), user.get(), image);
			storageService.saveInteractions(score.get(), user.get(), interactions);
		} catch (Exception e) {
			throw new ErrorException(String.format(SAVE_ANNOTATION_ERROR, scoreId));
		}
		
		Annotation annotation = new Annotation(new AnnotationIdentity(user.get().getId(), score.get().getId()));
		annotationRepository.save(annotation);
		log.info("Stored annotations for score " + scoreId + "[" + db + "] by user " + user.get().getUsername());
	}
	
	@GetMapping("/{db}/{userId}/pending")
	@ResponseStatus(HttpStatus.OK)
	public ScoreDto getNotAnnotatedScoreForUser(@PathVariable("db") String db, @PathVariable("userId") Long userId) throws NotFoundException, ErrorException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new NotFoundException(String.format(USER_ID_NOT_FOUND, userId));
		
		Optional<Score> score = scoreRepository.getRandomScore(db);
		if (!score.isPresent())
			throw new ErrorException(RANDOM_SCORE_ERROR);
			
		try {
			return scoreAssembler.getScoreDto(score.get());
		}
		catch (Exception e) {
			throw new ErrorException(String.format(REPRESENTATION_ERROR, score.get().getId()));
		}
	}
	
	@ExceptionHandler({NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse handleUserNotFoundException(NotFoundException e) {
		log.info(e.getCustomMessage());
		return new ExceptionResponse(e);
	}

	@ExceptionHandler({ErrorException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleUserNotFoundException(ErrorException e) {
		log.error(e.getCustomMessage());
		return new ExceptionResponse(e);
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
