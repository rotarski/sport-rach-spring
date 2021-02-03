package com.sport.rach.klub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.rach.klub.model.Czlonek;

@Repository
public interface CzlonekRepository extends JpaRepository<Czlonek, Long> {
	
	@Query("SELECT c FROM Czlonek c INNER JOIN c.organ o WHERE o.id=:id order by c.nazwisko")
	List<Czlonek> findSkladByIdOrgan(@Param("id") Long id);

	@Query("SELECT count(c)>0 FROM Klub k JOIN k.zarzad z JOIN z.sklad c JOIN k.nadzor n JOIN n.sklad c WHERE (k.id = :klubId AND c.id = :id)")
	boolean existsCzlonekByIdAndByKlubId(@Param("klubId") Long klubId, @Param("id")Long id);
}
