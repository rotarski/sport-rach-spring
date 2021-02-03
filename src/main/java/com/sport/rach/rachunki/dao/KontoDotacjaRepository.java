package com.sport.rach.rachunki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sport.rach.rachunki.model.KontoDotacja;

@Repository
public interface KontoDotacjaRepository extends JpaRepository<KontoDotacja, Long>{

}
