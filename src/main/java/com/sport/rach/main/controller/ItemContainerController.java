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

import com.sport.rach.main.dao.ItemContainerRepository;
import com.sport.rach.main.model.ItemContainer;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/main/")
public class ItemContainerController {
	
	@Autowired
	ItemContainerRepository itemContainerRepository;
	
	@GetMapping("items/all")
	public ResponseEntity<List<ItemContainer>> getItems(){
		List<ItemContainer> items = this.itemContainerRepository.findAllByOrderBySortIdDesc();
		return ResponseEntity.ok(items);		
	}
	
	@GetMapping("items")
	public ResponseEntity<List<ItemContainer>> getItemsNotNull(){
		List<ItemContainer> items = this.itemContainerRepository.findBySortIdGreaterThanOrderBySortIdDesc(0);
		return ResponseEntity.ok(items);		
	}
	
	@PostMapping("items")
	public ResponseEntity<?> saveItem(@Valid @RequestBody ItemContainer item, UriComponentsBuilder ucb){
		try {
			item = this.itemContainerRepository.save(item);
			URI location = ucb.path("/main/items/")
					.path(item.getId().toString())
					.build()
					.toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			return new ResponseEntity<ItemContainer>(item, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	
	@DeleteMapping("items/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long id){
		try {
			this.itemContainerRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
}
