package com.sport.rach.main.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sport.rach.main.dao.MainContainerRepository;
import com.sport.rach.main.model.MainContainer;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/main/")
public class MainContainerController {
	
	@Autowired
	MainContainerRepository mainContainerRepository;
	
	@GetMapping("mains/all")
	public ResponseEntity<List<MainContainer>> getItems(){
		List<MainContainer> items = this.mainContainerRepository.findAllByOrderBySortIdDesc();
		return ResponseEntity.ok(items);		
	}
	
	@GetMapping("mains")
	public ResponseEntity<List<MainContainer>> getItemsNotNull(){
		List<MainContainer> items = this.mainContainerRepository.findBySortIdGreaterThanOrderBySortIdDesc(0);
		return ResponseEntity.ok(items);		
	}
	
	@PostMapping("mains")
	public ResponseEntity<?> saveItem(@Valid @RequestBody MainContainer item, UriComponentsBuilder ucb){
		try {
			item = this.mainContainerRepository.save(item);
			URI location = ucb.path("/main/mains/")
					.path(item.getId().toString())
					.build()
					.toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			return new ResponseEntity<MainContainer>(item, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	
	@DeleteMapping("mains/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long id){
		try {
			this.mainContainerRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

}
