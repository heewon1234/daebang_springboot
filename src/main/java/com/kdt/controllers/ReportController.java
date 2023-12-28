package com.kdt.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MyReportDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.services.ReportService;

@RestController
@RequestMapping("/api/report/")
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	private ReportService rServ;
	
	@GetMapping("selectAll")
	public ResponseEntity<List<ReportDTO>> selectAll() {
		List<ReportDTO> list = rServ.selectAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("myReport/{id}")
	public ResponseEntity<List<MyReportDTO>> myReport(@PathVariable String id) {
		List<MyReportDTO> list = rServ.myReport(id);
		return ResponseEntity.ok(list);
	}
	@ExceptionHandler
	public ResponseEntity<String> excetion(Exception e){
		e.printStackTrace();
		logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제할 대상이 존재하지 않습니다.");
	}

}
