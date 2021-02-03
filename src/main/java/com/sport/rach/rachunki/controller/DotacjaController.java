package com.sport.rach.rachunki.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.rach.auth.services.UserDetailsImpl;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.rachunki.dao.DotacjaRepository;
import com.sport.rach.rachunki.dao.KosztyZdRepository;
import com.sport.rach.rachunki.model.Dotacja;
import com.sport.rach.rachunki.model.KosztyZd;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/klub/")
public class DotacjaController {

	@Autowired
	DotacjaRepository dotacjaRepository;
	
	@Autowired
	KlubRepository klubRepository;
	
	
	
	@GetMapping("dotacja")
	public ResponseEntity<List<Dotacja>> getAllDotacje(HttpServletRequest request){
		Long klubId = ((UserDetailsImpl)request.getAttribute("user")).getKlubId();
		List<Dotacja> dotacje= this.dotacjaRepository.findAllByKlubId(klubId);
		
		return ResponseEntity.ok(dotacje);
	}

	@PostMapping("dotacja")
	public ResponseEntity<Void> saveDotacja(@RequestBody Dotacja dotacja,  HttpServletRequest request){
		Long klubId = ((UserDetailsImpl)request.getAttribute("user")).getKlubId();

		this.dotacjaRepository.saveDotacja(dotacja, klubId);
		
		return ResponseEntity.ok().build();
	}
}
