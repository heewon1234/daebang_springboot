package com.kdt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdt.domain.entities.NewEstate;
import com.kdt.domain.entities.NewMember;

@Repository
public interface NewEstateRepository extends JpaRepository<NewEstate, Long> {
	NewEstate findByEstateDate(LocalDate estateDate);
	List<NewEstate> findByEstateDateBetween(LocalDate startDate, LocalDate endDate);
}
