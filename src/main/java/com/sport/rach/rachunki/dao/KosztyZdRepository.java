package com.sport.rach.rachunki.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sport.rach.rachunki.model.KosztyZd;
import com.sport.rach.rachunki.model.PrzychodyZd;
@Repository
public interface KosztyZdRepository extends JpaRepository<KosztyZd, Long>{
//	@Query("SELECT p FROM KosztyZd p JOIN p.klub k WHERE k.id=:id")
//	List<KosztyZd> finadAllByKlubId(@Param("id") Long id);
	
	public void saveNewKoszt(KosztyZd kosztyZd, Long klubId, Long podmiotId);
	
	@Query("SELECT p FROM KosztyZd p JOIN FETCH p.podmiot po JOIN p.klub k WHERE k.id=:id")
	List<KosztyZd> finadAllByKlubId(@Param("id") Long id);
	
	public void deleteZdarzenie(Long id, Long klubId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM rach_koszty WHERE klub_id=:klubId", nativeQuery = true)
	void deleteAllKosztyByKlubId(@Param("klubId") Long klubId); 
}
