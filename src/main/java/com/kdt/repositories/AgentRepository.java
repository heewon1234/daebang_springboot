package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.RealEstateAgent;

public interface AgentRepository extends JpaRepository<RealEstateAgent, String>  {
	RealEstateAgent findByEstateNumber(String number);
	
	@Modifying
	@Query("update RealEstateAgent a set a.pw=?2 where a.id=?1")
	int changePw(String id, String pw);
	
	@Modifying
	@Query("update RealEstateAgent a set a.manners_temperature=?1 where a.estateNumber=?2")
	void updateMannerTemp(double avgScore, String estateNumber);
	
	List<RealEstateAgent> findAllByEmail(String email);
	
	
}
