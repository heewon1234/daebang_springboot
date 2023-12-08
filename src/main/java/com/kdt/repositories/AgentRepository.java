package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Real_Estate_Agent;

public interface AgentRepository extends JpaRepository<Real_Estate_Agent, String>  {

}
