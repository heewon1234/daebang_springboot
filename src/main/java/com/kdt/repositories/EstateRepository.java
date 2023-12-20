package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long> {
	@EntityGraph(attributePaths = { "building", "heatingSystem", "room", "structure", "transaction", "images",
			"optionList","realEstateAgent" })
//	List<Estate> findAllByWriter(String loginId);
	List<Estate> findAllByRealEstateAgentEmail(String email);
	
	@Query("select m from Estate m order by estateId desc limit 6")
	 List<Estate> findTop6ByOrderByEstateIdDesc();
}
