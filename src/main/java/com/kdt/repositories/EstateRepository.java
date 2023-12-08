package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long>{
}
