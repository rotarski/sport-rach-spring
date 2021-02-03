package com.sport.rach.rachunki.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.rach.auth.services.UserDetailsImpl;
import com.sport.rach.rachunki.dao.PrzychodyZdRepository;
import com.sport.rach.rachunki.model.KosztyZd;
import com.sport.rach.rachunki.model.PrzychodyZd;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/klub/")
public class PrzychodyZdController {
	@Autowired
	PrzychodyZdRepository  przychodyZdRepository;

	@PreAuthorize("#klubId == authentication.principal.klubId")
	@GetMapping("{klubId}/przychody")
	public ResponseEntity<List<PrzychodyZd>> getPrzychody(@PathVariable Long klubId){

		List<PrzychodyZd> przychody= this.przychodyZdRepository.finadAllByKlubId(klubId);		
		return ResponseEntity.ok(przychody);
	}
	
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@PostMapping("{klubId}/przychody")
	public ResponseEntity<Void> saveKoszty(@RequestBody PrzychodyZd zdarzenie, @PathVariable Long klubId){

		this.przychodyZdRepository.saveNewPrzychod(zdarzenie, klubId, zdarzenie.getPodmiot().getId().longValue());
		
		return ResponseEntity.ok().build();
	}
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@DeleteMapping("{klubId}/przychody/{id}")
	public ResponseEntity<Void> deleteKoszty(@PathVariable Long klubId, @PathVariable Long id){

		this.przychodyZdRepository.deleteZdarzenie(id, klubId);
		
		return ResponseEntity.ok().build();
	}
}
