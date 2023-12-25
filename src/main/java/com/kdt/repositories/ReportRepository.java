package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Report;
import com.kdt.dto.ReportDTO;

public interface ReportRepository extends JpaRepository<Report, String> {
//    @EntityGraph(attributePaths = {"estate", "reportStatus", "reportContents", "realEstateAgent"})
//    List<Report> findAll();
	@Query("SELECT COUNT(r) FROM Report r WHERE r.reportStatus.status = '대기중'")
	Long countByReportStatus();
	
	@Query("SELECT r FROM Report r WHERE r.reportStatus.status = '대기중'")
	List<Report> selectAllByReportStatus();

}

