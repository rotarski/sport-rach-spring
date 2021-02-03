package com.sport.rach.klub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sport.rach.klub.model.Czlonek;
import com.sport.rach.klub.service.CzlonekService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/klub/")
public class CzlonekController {
	
	@Autowired
	CzlonekService czlonekService;
	
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@PostMapping(value = "{klubId}/nadzor/czlonek", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCzlonekToNadzor(
			@PathVariable("klubId") Long klubId, 
			@RequestBody Czlonek czlonek) throws Exception{
		this.czlonekService.addCzlonekToNadzor(czlonek, klubId);
		return ResponseEntity.ok().build();	
	}
	
	@PreAuthorize("#klubId == authentication.principal.klubId")
	@DeleteMapping("{klubId}/nadzor/czlonek/{czlonekId}")
	public ResponseEntity<?> removeCzlonekFromNadzor( @PathVariable Long klubId, @PathVariable Long czlonekId){

		try {			
				this.czlonekService.removeCzlonekFromNadzor(czlonekId, klubId);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("#klubId == authentication.principal.klubId && @czlonekRepository.existsCzlonekByIdAndByKlubId(#klubId, #czlonek.getId())")
	@PutMapping(value = "{klubId}/czlonek", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCzlonekToNadzor(
			@PathVariable("klubId") Long klubId,
			@RequestBody Czlonek czlonek) throws Exception{
		this.czlonekService.updateCzlonek(czlonek);;
		return ResponseEntity.ok().build();	
	}
	
	@PreAuthorize("#klubId == authentication.principal.klubId && @czlonekRepository.existsCzlonekByIdAndByKlubId(#klubId, #czlonekId)")
	@DeleteMapping("{klubId}/czlonek/{czlonekId}")
	public ResponseEntity<?> removeCzlonek( @PathVariable Long klubId, @PathVariable Long czlonekId){

		try {			
				this.czlonekService.removeCzlonek(czlonekId);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

}
