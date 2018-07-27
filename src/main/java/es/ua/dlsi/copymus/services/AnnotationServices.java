package es.ua.dlsi.copymus.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import es.ua.dlsi.copymus.models.Score;
import es.ua.dlsi.copymus.models.ScoreRepository;
import es.ua.dlsi.copymus.models.User;
import es.ua.dlsi.copymus.models.UserRepository;

public class AnnotationServices {
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean annotate(String score_id, Long user_id) {
		Optional<Score> optScore = scoreRepository.findById(score_id);
		if (!optScore.isPresent()) return false;
		Score score = optScore.get();
		
		Optional<User> optUser = userRepository.findById(user_id);
		if (!optUser.isPresent()) return false;
		User user = optUser.get();
		
		Set<Score> scores = user.getAnnotatedScores();
		if (scores == null) scores = new HashSet<Score>();
				
		return true;
	}
}
