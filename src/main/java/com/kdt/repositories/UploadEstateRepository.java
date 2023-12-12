package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadEstate;

public interface UploadEstateRepository extends JpaRepository<UploadEstate, Long>{
}
