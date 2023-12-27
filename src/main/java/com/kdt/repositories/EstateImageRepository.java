package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.EstateImage;
import com.kdt.domain.entities.RealEstateAgent;

public interface EstateImageRepository extends JpaRepository<EstateImage, Long>{
	Long deleteByParentId(Long parentId);
	
	// 지울 파일 이름 검색
	List<EstateImage> findAllByParentId(Long parentId);
	
	@Query("select m.sysName from EstateImage m where m.parentId = ?1 order by m.seq limit 1")
	List<String> selectbyparentIdordertBySeq(Long parentId);
}
