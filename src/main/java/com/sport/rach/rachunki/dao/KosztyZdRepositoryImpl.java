package com.sport.rach.rachunki.dao;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.rachunki.model.KosztyZd;
import com.sport.rach.rachunki.model.Podmiot;

@Transactional
@Component
public class KosztyZdRepositoryImpl {
	
	@Autowired
	PodmiotRepository podmiotRepository;
	
	@Autowired
	KlubRepository klubRepository;
	
	@Autowired
	KosztyZdRepository kosztyZdRepository;
	
	public void saveNewKoszt(KosztyZd kosztyZd, Long klubId, Long podmiotId){
		Klub klub = this.klubRepository.findById(klubId).get();
		Podmiot podmiot = this.podmiotRepository.findById(podmiotId).get();
		kosztyZd.setKlub(klub);
		kosztyZd.setPodmiot(podmiot);
		this.kosztyZdRepository.save(kosztyZd);
	}
	
	@Transactional
	public void deleteZdarzenie(Long id, Long klubId) {
		KosztyZd zdarzenie = kosztyZdRepository.findById(id).get();
		Long kId = zdarzenie.getKlub().getId();
		if(kId.equals(klubId)) {
			kosztyZdRepository.delete(zdarzenie);
		}else {
			throw new IllegalArgumentException("Błędny id lub pozycja niedozwolona dla danego klubu");
		}
	}

}
