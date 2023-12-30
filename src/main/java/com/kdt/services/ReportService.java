package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.MyReport;
import com.kdt.domain.entities.Report;
import com.kdt.domain.entities.ReportStatus;
import com.kdt.dto.MyReportDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.mappers.MyReportMapper;
import com.kdt.mappers.ReportMapper;
import com.kdt.repositories.MyReportRepository;
import com.kdt.repositories.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository rRepo;
	
	@Autowired
	private ReportMapper rMapper;
	
	@Autowired
	private MyReportRepository mRepo;
	
	@Autowired
	private MyReportMapper mMapper;
	
	public List<ReportDTO> selectAllByReportStatus() {
		
		List<Report> list = rRepo.selectAllByReportStatus();
		List<ReportDTO> dtos = rMapper.toDtoList(list);
        return dtos;
	}
	
	public Long countByReportStatus() {
		return rRepo.countByReportStatus();
	}
	
//	public List<ReportDTO> selectAll() {
//        List<Report> list = rRepo.findAll();
//        List<ReportDTO> dtos = rMapper.toDtoList(list);
//
//
//        return dtos;
//    }
	public List<ReportDTO> selectAllByWriteDateDESC() {
        List<Report> list = rRepo.findAllByOrderByWriteDateDesc();
        List<ReportDTO> dtos = rMapper.toDtoList(list);
        return dtos;
    }
	public void approve(Long seq) {
		Report e = rRepo.findBySeq(seq);
		ReportStatus reportStatus = new ReportStatus();
		reportStatus.setId("rs2");
		e.setReportStatus(reportStatus);

		rRepo.save(e);
	}
	public void revoke_approval(Long seq) {
		Report e = rRepo.findBySeq(seq);
		ReportStatus reportStatus = new ReportStatus();
		reportStatus.setId("rs1");
		e.setReportStatus(reportStatus);
		rRepo.save(e);
	}
	
	public void reject(Long seq) {
		Report e = rRepo.findBySeq(seq);
		ReportStatus reportStatus = new ReportStatus();
		reportStatus.setId("rs3");
		e.setReportStatus(reportStatus);
		rRepo.save(e);
	}
	
	public void revoke_rejection(Long seq) {
		Report e = rRepo.findBySeq(seq);
		ReportStatus reportStatus = new ReportStatus();
		reportStatus.setId("rs1");
		e.setReportStatus(reportStatus);
		rRepo.save(e);
	}
	public void deleteBySeq(Long seq) {
		Report e = rRepo.findBySeq(seq);
		rRepo.delete(e);
	}
	
	public List<MyReportDTO> myReport(String id) {
		List<MyReport> list = mRepo.myReport(id);
		List<MyReportDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
}
