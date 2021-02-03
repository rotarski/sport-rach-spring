package com.sport.rach.rachunki.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.rachunki.model.Dotacja;

@Transactional
@Component
public class DotacjaRepositoryImpl {
	
	@Autowired
	KlubRepository klubRepository;
	
	@Autowired
	DotacjaRepository dotacjaRepository;

	public void saveDotacja(Dotacja dotacja, Long klubId) {
		Klub klub = this.klubRepository.findById(klubId).get();
		dotacja.setKlub(klub);
		this.dotacjaRepository.save(dotacja);
		
	}
}
