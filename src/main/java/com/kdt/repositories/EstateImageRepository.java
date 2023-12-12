package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadEstate;
import com.kdt.domain.entities.EstateImage;

public interface EstateImageRepository extends JpaRepository<EstateImage, Long>{

}
