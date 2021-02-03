package com.sport.rach.rachunki.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.rachunki.model.Podmiot;
import com.sport.rach.rachunki.model.PrzychodyZd;

@Component
@Transactional
public class PrzychodyZdRepositoryImpl {
	
	@Autowired
	PodmiotRepository podmiotRepository;
	
	@Autowired
	KlubRepository klubRepository;
	
	@Autowired
	PrzychodyZdRepository przychodyZdRepository;
	
	public void saveNewPrzychod(PrzychodyZd przychodyZd, Long klubId, Long podmiotId){
		Klub klub = this.klubRepository.findById(klubId).get();
		Podmiot podmiot = this.podmiotRepository.findById(podmiotId).get();
		przychodyZd.setKlub(klub);
		przychodyZd.setPodmiot(podmiot);
		this.przychodyZdRepository.save(przychodyZd);
	}
	

	public void deleteZdarzenie(Long id, Long klubId) {
		PrzychodyZd zdarzenie = przychodyZdRepository.findById(id).get();
		Long kId = zdarzenie.getKlub().getId();
		if(kId.equals(klubId)) {
			przychodyZdRepository.delete(zdarzenie);
		}else {
			throw new IllegalArgumentException("Błędny id lub pozycja niedozwolona dla danego klubu");
		}
	}

}
