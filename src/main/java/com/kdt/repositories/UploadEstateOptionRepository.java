package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadEstateOption;

public interface UploadEstateOptionRepository extends JpaRepository<UploadEstateOption, Long>{
	Long deleteByEstateCode(Long esateId);
}
