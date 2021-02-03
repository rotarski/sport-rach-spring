package com.sport.rach.rachunki.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sport.rach.rachunki.model.PrzychodyZd;
@Repository
public interface PrzychodyZdRepository extends JpaRepository<PrzychodyZd, Long>{

	@Query("SELECT p FROM PrzychodyZd p JOIN FETCH p.podmiot po JOIN p.klub k WHERE k.id=:id")
	List<PrzychodyZd> finadAllByKlubId(@Param("id") Long id);
	
	public void saveNewPrzychod(PrzychodyZd przychodyZd, Long klubId, Long podmiotId);
	
	public void deleteZdarzenie(Long id, Long klubId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM rach_przychody WHERE klub_id =:klubId", nativeQuery = true)
	void deleteAllPrzychodyByKlubId(@Param("klubId") Long klubId); 

}
