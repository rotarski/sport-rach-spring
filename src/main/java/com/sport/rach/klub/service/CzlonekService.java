package com.sport.rach.klub.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sport.rach.klub.dao.CzlonekRepository;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.model.Czlonek;
import com.sport.rach.klub.model.Organ;

@Service
public class CzlonekService {
	
	@Autowired
	private KlubRepository klubRepository;

	
	@Autowired
	private CzlonekRepository czlonekRepository;

	@Transactional
	public void addCzlonekToNadzor(Czlonek czlonek, Long klubId) throws Exception {

		Organ organ = this.klubRepository.findById(klubId).get().getNadzor();
		if(organ != null) {
			organ.getSklad().add(czlonek);
			czlonek.setOrgan(organ);
		}else {
			throw new Exception("Organ nadzoru nie jest zarejestrowany");
		}
	}
	@Transactional
	public void addCzlonekToZarzad(Czlonek czlonek, Long klubId) throws Exception {

		Organ organ = this.klubRepository.findById(klubId).get().getZarzad();
		if(organ != null) {
			organ.getSklad().add(czlonek);
			czlonek.setOrgan(organ);
		}else {
			throw new Exception("Organ zarzad nie jest zarejestrowany");
		}
	}
	
	@Transactional
	public void removeCzlonekFromNadzor(Long czlonekId, Long klubId) {
		Organ organ = this.klubRepository.findById(klubId).get().getNadzor();
		Czlonek czlonekForDelete = this.czlonekRepository.findById(czlonekId).get();
		organ.getSklad().remove(czlonekForDelete);
	}
	@Transactional
	public void removeCzlonek(Long czlonekId) {
		Czlonek czlonekToDelete = this.czlonekRepository.findById(czlonekId).get();
		czlonekToDelete.setOrgan(null);
		this.czlonekRepository.deleteById(czlonekId);
	}
	
	@Transactional
	public void updateCzlonek(Czlonek czlonek) {
		Czlonek czlonekToUpdated = this.czlonekRepository.findById(czlonek.getId()).get();
		czlonek.setOrgan(czlonekToUpdated.getOrgan());
		this.czlonekRepository.save(czlonek);
	}
	
	
}
