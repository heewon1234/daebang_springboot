package com.kdt.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Real_Estate_Agent;
import com.kdt.domain.entities.Visitor;

public interface AgentRepository extends JpaRepository<Real_Estate_Agent, String>  {
	Real_Estate_Agent findByEstateNumber(String number);
}
