package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.EstateTemp;

public interface EstateTempRepository extends JpaRepository<EstateTemp, Long>{
}
