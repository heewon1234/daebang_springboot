package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Estate;

public interface MapRepository extends JpaRepository<Estate, Long>{

}
