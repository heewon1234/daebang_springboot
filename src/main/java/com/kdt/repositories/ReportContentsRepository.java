package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.ReportContents;

public interface ReportContentsRepository extends JpaRepository<ReportContents, String> {

}

