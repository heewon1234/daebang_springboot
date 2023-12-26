package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.MyReport;

public interface MyReportRepository extends JpaRepository<MyReport, String>{

	@Query("select new com.kdt.domain.entities.MyReport(r.realEstateAgent.estateName, r.reportContents.content, r.content, r.reportStatus.status) "
		+ "from Report r "
		+ "where r.writer = ?1")
	List<MyReport> myReport(String id);
}
