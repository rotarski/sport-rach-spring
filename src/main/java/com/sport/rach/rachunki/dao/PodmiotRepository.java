package com.sport.rach.rachunki.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sport.rach.rachunki.model.Podmiot;
@Repository
public interface PodmiotRepository extends JpaRepository<Podmiot, Long>{
	@Query("SELECT p FROM Podmiot p JOIN FETCH p.klub k WHERE k.id=:id")
	List<Podmiot> finadFetchAllByKlubId(@Param("id") Long id);
	
	Podmiot savePodmiot(Podmiot podmiot,Long klubId);
	
	void deleteByIdAndKlub(Long id, Long klubId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM rach_podmiot  WHERE klub_id = :klubId", nativeQuery = true)
	void deleteAllPodmiotByKlubId(@Param("klubId") Long klubId);
	
}
