package com.sport.rach.klub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sport.rach.klub.model.Czlonek;

@Repository
public interface RejestracjaRepository extends JpaRepository<Czlonek, Long> {

}
