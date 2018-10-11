package es.ua.dlsi.copymus.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ua.dlsi.copymus.dto.ScoreDTO;
import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.models.ScoreRepository;
import es.ua.dlsi.copymus.models.User;
import es.ua.dlsi.copymus.models.UserRepository;

@RestController
@RequestMapping("/score")
public class ScoreController {
	
	private final Logger log = LoggerFactory.getLogger(ScoreController.class);
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/{db}/{userId}/pending")
	public ResponseEntity<ScoreDTO> getNotAnnotatedScoreForUser(@PathVariable("db") String db, @PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			return ResponseEntity.notFound().build();
		
		Score score = scoreRepository.getRandomScore();
		ScoreDTO dto = ScoreDTO.fromScore(score);
		
		log.debug(score.toString());
		
		return ResponseEntity.ok(dto);
	}
}
