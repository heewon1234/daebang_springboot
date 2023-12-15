package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdt.domain.entities.MapRegion;

public interface MapRegionRepository extends JpaRepository<MapRegion, Long>{
	
	@Query("SELECT m FROM MapRegion m " +
	           "WHERE (m.sido LIKE CONCAT(:keyword, '%') AND m.sigungu IS NULL AND m.eup_myeon_dong_gu IS NULL " +
	           "AND m.eup_myeon_re_dong IS NULL AND m.re IS NULL) " +
	           "OR (m.sigungu LIKE CONCAT(:keyword, '%') AND m.eup_myeon_dong_gu IS NULL " +
	           "AND m.eup_myeon_re_dong IS NULL AND m.re IS NULL) " +
	           "OR (m.eup_myeon_dong_gu LIKE CONCAT(:keyword, '%') AND m.eup_myeon_re_dong IS NULL AND m.re IS NULL) " +
	           "OR (m.eup_myeon_re_dong LIKE CONCAT(:keyword, '%') AND m.re IS NULL) " +
	           "OR (m.re LIKE CONCAT(:keyword, '%'))")
	    List<MapRegion> selectByKeyword(@Param("keyword") String keyword);	

}
