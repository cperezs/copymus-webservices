package es.ua.dlsi.grfia.hispamus.controllers;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.ua.dlsi.grfia.hispamus.models.User;
import es.ua.dlsi.grfia.hispamus.models.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public Iterable<User> get() {
		return userRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<User> post(@RequestBody User data) {
		if (data.getUsername() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		if (userRepository.findByUsernameIgnoreCase(data.getUsername()).isPresent()) {
			log.info("POST failed to create " + data.toString());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		User user = userRepository.save(data);
		log.info("POST created " + user.toString());
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> put(@PathVariable Long id, @RequestBody User data) {
		if (data.getUsername() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<User> result = userRepository.findById(id);
		if (result.isPresent()) {
			User user = result.get();

			Optional<User> resultByName = userRepository.findByUsernameIgnoreCase(data.getUsername());
			if (resultByName.isPresent() && resultByName.get().getId().longValue() != user.getId().longValue()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
			user.setUsername(data.getUsername());
			user = userRepository.save(user);
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
