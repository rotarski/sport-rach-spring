package com.sport.rach.rachunki.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sport.rach.auth.services.UserDetailsImpl;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.rachunki.dao.KosztyZdRepository;
import com.sport.rach.rachunki.dao.PrzychodyZdRepository;
import com.sport.rach.rachunki.model.KosztyZd;
import com.sport.rach.rachunki.model.PrzychodyZd;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/klub/")
public class KosztyZdController {
	
	

	@Autowired
	KosztyZdRepository kosztyZdRepository;
	
	@Autowired
	KlubRepository klubRepository;
	
	
	@PreAuthorize("#id == authentication.principal.klubId")
	@GetMapping("{id}/koszty")
	public ResponseEntity<List<KosztyZd>> getKoszty(@PathVariable Long id){
		//Long klubId = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKlubId();
		List<KosztyZd> koszty= this.kosztyZdRepository.finadAllByKlubId(id);
		
		return ResponseEntity.ok(koszty);
	}
	@PreAuthorize("#id == authentication.principal.klubId")
	@PostMapping("{id}/koszty")
	public ResponseEntity<Void> saveKoszty(@RequestBody KosztyZd zdarzenie, @PathVariable Long id){
		//Long klubId = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKlubId();

		this.kosztyZdRepository.saveNewKoszt(zdarzenie, id, zdarzenie.getPodmiot().getId().longValue());
		
		return ResponseEntity.ok().build();
	}
	@PreAuthorize("#id == authentication.principal.klubId")
	@DeleteMapping("{klubId}/koszty/{id}")
	public ResponseEntity<Void> deleteKoszty(@PathVariable Long klubId, @PathVariable Long id){

		this.kosztyZdRepository.deleteZdarzenie(id, klubId);
		
		return ResponseEntity.ok().build();
	}
}
