package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.MapSubway;

public interface MapSubwayRepository extends JpaRepository<MapSubway, Long>{
	
	List<MapSubway> findByNameStartingWith(String keyword);
	
}
