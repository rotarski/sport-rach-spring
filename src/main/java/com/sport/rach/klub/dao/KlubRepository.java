package com.sport.rach.klub.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import com.sport.rach.klub.model.Klub;





@Repository
public interface KlubRepository extends JpaRepository<Klub, Long>{
	//@PreAuthorize("#id == authentication.principal.klubId")
	public Klub getKlubFetch(Long id);
	
	@Query("SELECT k FROM Klub k "
			+ "LEFT JOIN FETCH k.rejestracja "
			+ "LEFT JOIN FETCH k.zarzad z "		
			+ "LEFT JOIN FETCH k.nadzor n "	
			+ "WHERE k.id=:id ")
	public Klub findFetchKlub(@Param("id") Long id);
	
	public void removeKlubfirstRemoveSklady(Long id); 
	
	public Optional<Klub> saveKlub(Klub klub, Long userId);
	
	public Optional<Klub> updateKlub(Klub klub);
	
	public void deleteKlubByUserId(Long userId);
}
