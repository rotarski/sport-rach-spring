package com.sport.rach.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sport.rach.main.model.ItemContainer;

public interface ItemContainerRepository extends JpaRepository<ItemContainer, Long>{

	List<ItemContainer>findAllByOrderBySortIdDesc();
	List<ItemContainer>findBySortIdGreaterThanOrderBySortIdDesc(Integer sortId);
}
