package com.kdt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.MyReport;
import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.domain.entities.Report;
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
	public List<ReportDTO> getAll() {
        List<Report> list = rRepo.findAll();
        List<ReportDTO> dtos = rMapper.toDtoList(list);
        return dtos;
    }
	public void approve(Long seq) {
		Report e = rRepo.findBySeq(seq);
		System.out.println("확인"+e);
		e.setStatus_code("rs2");
		rRepo.save(e);
	}
	public void revoke_approval(Long seq) {
		Report e = rRepo.findBySeq(seq);
		e.setStatus_code("rs1");
		rRepo.save(e);
	}
	
	public void reject(Long seq) {
		Report e = rRepo.findBySeq(seq);
		System.out.println("확인"+e);
		e.setStatus_code("rs3");
		rRepo.save(e);
	}
	
	public void revoke_rejection(Long seq) {
		Report e = rRepo.findBySeq(seq);
		e.setStatus_code("rs1");
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
