package com.sport.rach.rachunki.dao;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.rachunki.model.Podmiot;

@Component
public class PodmiotRepositoryImpl {
	
	@Autowired
	KlubRepository klubRepository;
	
	@Autowired
	PodmiotRepository podmiotRepository;
	
	@Transactional
	public Podmiot savePodmiot(Podmiot podmiot, Long klubId){
		Klub klub = this.klubRepository.findById(klubId).get();
		podmiot.setKlub(klub);
		return this.podmiotRepository.saveAndFlush(podmiot);
	}
	@Transactional
	void deleteByIdAndKlub(Long id, Long klubId) {
		Podmiot podmiot = podmiotRepository.findById(id).get();
		Long kId = podmiot.getKlub().getId();
		if(kId.equals(klubId)) {
			podmiotRepository.delete(podmiot);
		}else {
			throw new IllegalArgumentException("Błędny id lub pozycja niedozwolona dla danego klubu");
		}
	}
	

	

}
