package com.sport.rach.user.controller;


import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.sport.rach.user.dao.UserRepository;
import com.sport.rach.user.model.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping(value = "/main/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable String email){
		
		Optional<User> optionalUser = repo.findByEmail(email);
		if(optionalUser.isEmpty()) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else {
			return ResponseEntity.ok(optionalUser.get());
		}
	}
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user, UriComponentsBuilder ucb){
		User newUser;
		try {
			newUser = this.repo.save(user);
			URI location = 
					ucb.path("/user/")
					.path(String.valueOf(newUser.getId()))
					.build()
					.toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
//	@ExceptionHandler(ConstraintViolationException.class)
//	  @ResponseStatus(HttpStatus.BAD_REQUEST)
//	  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
//	    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//	  }

}
