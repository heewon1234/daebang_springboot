package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.MapSchool;

public interface MapSchoolRepository extends JpaRepository<MapSchool, Long>{
	
	List<MapSchool> findByNameStartingWith(String keyword);
	
}
