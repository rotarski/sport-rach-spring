package com.sport.rach.klub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sport.rach.klub.model.Organ;

@Repository
public interface OrganRepository extends JpaRepository<Organ, Long> {
	
//	public void deleteZarzad(Long klubId); 
//	
//	public void deleteNadzor(Long klubId); 
}
