package com.kdt.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Real_Estate_Agent;
import com.kdt.domain.entities.Visitor;

public interface AgentRepository extends JpaRepository<Real_Estate_Agent, String>  {
	Real_Estate_Agent findByEstateNumber(String number);
	
	@Modifying
	@Query("update Real_Estate_Agent a set a.pw=?2 where a.id=?1")
	int changePw(String id, String pw);
	
	@Modifying
	@Query("update Real_Estate_Agent a set a.manners_temperature=?1 where a.estateNumber=?2")
	void updateMannerTemp(double avgScore, String estateNumber);
}
