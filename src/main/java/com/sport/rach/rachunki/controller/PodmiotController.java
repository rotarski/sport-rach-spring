package com.sport.rach.rachunki.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sport.rach.auth.services.UserDetailsImpl;
import com.sport.rach.rachunki.dao.PodmiotRepository;
import com.sport.rach.rachunki.model.Podmiot;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/klub/")
public class PodmiotController {
	
	@Autowired
	PodmiotRepository podmiotRepository;
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@GetMapping("{klubId}/podmioty")
	public ResponseEntity<List<Podmiot>> getPodmioty(@PathVariable Long klubId){

		List<Podmiot> koszty= this.podmiotRepository.finadFetchAllByKlubId(klubId);	
		return ResponseEntity.ok(koszty);
	}
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@PostMapping("{klubId}/podmioty")
	public ResponseEntity<Podmiot> savePodmiot(@RequestBody Podmiot podmiot, @PathVariable Long klubId){

		podmiot = this.podmiotRepository.savePodmiot(podmiot, klubId);	
		return ResponseEntity.ok(podmiot);
	}
}
