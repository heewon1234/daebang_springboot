package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Report;

public interface ReportRepository extends JpaRepository<Report, String>{
//	@EntityGraph(attributePaths = { "ReportStatus", "ReportContents" })
//	List<Report> findAll();
}
