package com.sport.rach.klub.service;



import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.rachunki.dao.KosztyZdRepository;
import com.sport.rach.rachunki.dao.PodmiotRepository;
import com.sport.rach.rachunki.dao.PrzychodyZdRepository;

@Service
public class KlubService {
	@Autowired
	private KlubRepository klubRepository;
	@Autowired
	private PodmiotRepository podmiotRepository;
	@Autowired
	private KosztyZdRepository kosztyZdRepository;
	@Autowired
	private PrzychodyZdRepository przychodyZdRepository;
	@Autowired

	
	public KlubService() {
	}



	public Optional<Klub> getKlub(Long id) {
		return Optional.of(this.klubRepository.getKlubFetch(id));

	}
	
	public Klub save(Klub klub) {
			return this.klubRepository.save(klub);
	}
	@Transactional
	public void deleteKlub(Long userId, Long klubId){	
		this.przychodyZdRepository.deleteAllPrzychodyByKlubId(klubId);
		this.kosztyZdRepository.deleteAllKosztyByKlubId(klubId);
		this.podmiotRepository.deleteAllPodmiotByKlubId(klubId);			
		this.klubRepository.deleteKlubByUserId(userId);
	}
	

	public Optional<Klub> saveKlub(Klub klub, Long userId){
		return this.klubRepository.saveKlub(klub, userId);
	}
	
	
	public void removeKlub(Long userId) {
		this.klubRepository.removeKlubfirstRemoveSklady(userId);
	}
	
	public Optional<Klub> updateKlub(Klub klub){
		return this.klubRepository.updateKlub(klub);
	}
	
	

}
