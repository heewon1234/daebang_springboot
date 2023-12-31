package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadEstate;

public interface UploadEstateRepository extends JpaRepository<UploadEstate, Long>{
	 List<UploadEstate> findByWriter(String loginId);
	 void deleteByWriter(String email);
	 
}
