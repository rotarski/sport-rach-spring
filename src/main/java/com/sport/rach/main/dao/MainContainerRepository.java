package com.sport.rach.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sport.rach.main.model.MainContainer;

@Repository
public interface MainContainerRepository extends JpaRepository<MainContainer, Long> {
	List<MainContainer>findAllByOrderBySortIdDesc();
	List<MainContainer>findBySortIdGreaterThanOrderBySortIdDesc(Integer sortId);
}
