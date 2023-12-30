package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.ReportStatus;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, String> {

}

