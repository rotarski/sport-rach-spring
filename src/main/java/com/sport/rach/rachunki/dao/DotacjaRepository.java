package com.sport.rach.rachunki.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sport.rach.rachunki.model.Dotacja;


@Repository
public interface DotacjaRepository extends JpaRepository<Dotacja, Long>{
	
	@Query("SELECT d FROM Dotacja d JOIN FETCH d.klub k WHERE k.id=:id")
	List<Dotacja> findAllByKlubId(@Param("id") Long id);

	public void saveDotacja(Dotacja dotacja, Long klubId);
}
