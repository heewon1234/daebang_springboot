package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.RealEstateViews;

public interface RealEstateViewsRepository extends JpaRepository<RealEstateViews, Long>  {
	RealEstateViews findByEstate_EstateId(Long estateId);
	
	@Query("SELECT r FROM RealEstateViews r ORDER BY viewCount DESC LIMIT 5")
	List<RealEstateViews> topFive();
}
