package com.sport.rach.klub.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.sport.rach.auth.services.UserDetailsImpl;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.klub.service.KlubService;
import com.sport.rach.user.dao.UserRepository;
import com.sport.rach.user.model.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class KlubController {

	@Autowired
	private KlubService klubService;

	@PreAuthorize("#id == authentication.principal.klubId")
	@GetMapping(value = "/klub/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Klub> getKlub(@PathVariable Long id) {

		Optional<Klub> klub = this.klubService.getKlub(id);
		if (klub.isPresent()) {
			return ResponseEntity.ok(klub.get());
		}
		return new ResponseEntity<Klub>(HttpStatus.NOT_FOUND);

	}
	@PreAuthorize("#klub.id == authentication.principal.klubId")
	@PostMapping(value = "/klub", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveKlub(@Valid @RequestBody Klub klub, UriComponentsBuilder ucb) {

		Long userId = 
				((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		try {

			klub = this.klubService.saveKlub(klub, userId).orElseThrow();
			URI location = ucb.path("/user/klub/").path(String.valueOf(klub.getId())).build().toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			return new ResponseEntity<Klub>(klub, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	@PreAuthorize("#id == authentication.principal.klubId")
	@DeleteMapping("/klub/{id}")
	public ResponseEntity<?> deleteKlub(@PathVariable Long id) {
		Long userId = 
				((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		try {
				this.klubService.deleteKlub(userId, id);
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
			}
	}
	@PreAuthorize("#klub.id == authentication.principal.klubId")
	@PutMapping(value = "/klub", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Klub> updateKlub(@RequestBody Klub klub, UriComponentsBuilder ucb) {
		
			try {
				klub = this.klubService.updateKlub(klub).get();
				URI location = ucb.path("/klub/").path(String.valueOf(klub.getId())).build().toUri();
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(location);
				return new ResponseEntity<Klub>(klub, headers, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Klub>(HttpStatus.NOT_ACCEPTABLE);
			}
	}

}
