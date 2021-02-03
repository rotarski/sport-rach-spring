package com.sport.rach.klub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.rach.klub.model.Czlonek;
import com.sport.rach.klub.model.Organ;
import com.sport.rach.klub.service.KlubService;
import com.sport.rach.klub.service.OrganService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/klub/")
public class OrganController {
	
	@Autowired
	KlubService klubService;
	
	@Autowired
	OrganService organService;
	
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@PostMapping(value = "{klubId}/zarzad", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Organ> saveZarzad(@RequestBody Organ zarzad, @PathVariable Long klubId){
		try {
			zarzad = this.organService.saveZarzad(zarzad, klubId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.ok(zarzad);
	}
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@DeleteMapping("{klubId}/zarzad")
	public ResponseEntity<?> removeZarzad( @PathVariable Long klubId){

		try {			
				this.organService.removeZarzad(klubId);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("#klubId == authentication.principal.klubId")
	@PostMapping(value = "{klubId}/nadzor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Organ> saveNadzor(@RequestBody Organ zarzad, @PathVariable Long klubId){
		try {
			zarzad = this.organService.saveZarzad(zarzad, klubId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.ok(zarzad);
	}
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@DeleteMapping("{klubId}/nadzor")
	public ResponseEntity<?> removeNadzor( @PathVariable Long klubId){

		try {			
				this.organService.removeZarzad(klubId);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
}
