package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.EstateImage;

public interface EstateImageRepository extends JpaRepository<EstateImage, Long>{
	Long deleteByParentId(Long parentId);
	
	// 지울 파일 이름 검색
	List<EstateImage> findAllByParentId(Long parentId);
}
