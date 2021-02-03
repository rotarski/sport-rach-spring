package com.sport.rach.klub.service;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sport.rach.klub.dao.CzlonekRepository;
import com.sport.rach.klub.dao.KlubRepository;
import com.sport.rach.klub.dao.OrganRepository;
import com.sport.rach.klub.model.Czlonek;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.klub.model.Organ;

@Service
public class OrganService {

	@Autowired
	private KlubRepository klubRepository;

	@Autowired
	private OrganRepository organRepository;
	
	@Autowired
	private CzlonekRepository czlonekRepository;

	@Transactional
	public Organ saveZarzad(Organ zarzad, Long klubId) throws Exception {
		Klub klub = this.klubRepository.findById(klubId)
				.orElseThrow(() -> new Exception("brak zarejestrowanego klubu"));

		klub.setZarzad(zarzad);
		return this.organRepository.save(zarzad);
	}

	@Transactional
	public void removeZarzad(Long klubId) throws Exception {
		Klub klub = this.klubRepository.findById(klubId)
				.orElseThrow(() -> new Exception("brak zarejestrowanego klubu"));

		if (klub.getZarzad() != null) {
			Long organId = klub.getZarzad().getId();
			klub.setZarzad(null);
			this.organRepository.deleteById(organId);
		}
	}
	@Transactional
	public Organ saveNadzor(Organ nadzor, Long klubId) throws Exception {
		Klub klub = this.klubRepository.findById(klubId)
				.orElseThrow(() -> new Exception("brak zarejestrowanego klubu"));

		klub.setNadzor(nadzor);
		return this.organRepository.save(nadzor);
	}

	@Transactional
	public void removeNadzor(Long klubId) throws Exception {
		Klub klub = this.klubRepository.findById(klubId)
				.orElseThrow(() -> new Exception("brak zarejestrowanego klubu"));

		if (klub.getNadzor() != null) {
			Long organId = klub.getNadzor().getId();
			klub.setZarzad(null);
			this.organRepository.deleteById(organId);
		}
	}
	
	
	
}
