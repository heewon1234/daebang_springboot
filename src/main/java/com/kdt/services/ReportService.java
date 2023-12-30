package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.MyReport;
import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.domain.entities.Report;
import com.kdt.domain.entities.ReportStatus;
import com.kdt.dto.MyReportDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.mappers.MyReportMapper;
import com.kdt.mappers.ReportMapper;
import com.kdt.repositories.AgentRepository;
import com.kdt.repositories.MyReportRepository;
import com.kdt.repositories.ReportRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ReportService {
	@Autowired
	private AgentRepository aRepo;
	
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
//	public void approve(Long seq) {
//		Report e = rRepo.findBySeq(seq);
//		ReportStatus reportStatus = new ReportStatus();
//		reportStatus.setId("rs2");
//		e.setReportStatus(reportStatus);
//		rRepo.save(e);
//		
//		 // RealEstateAgent의 report_Count 증가 로직
//	    RealEstateAgent agent = e.getRealEstateAgent();
//	    agent.setReport_Count(agent.getReport_Count() + 1);
//	    aRepo.save(agent);
//	}
	public void approve(Long seq) {
	    Report e = rRepo.findBySeq(seq);
	    ReportStatus reportStatus = new ReportStatus();
	    reportStatus.setId("rs2");
	    e.setReportStatus(reportStatus);
	    rRepo.save(e);

	    // RealEstateAgent의 report_Count 증가 로직
	    RealEstateAgent agent = e.getRealEstateAgent();
	    Long reportCount = agent.getReport_Count();

	    // 5의 배수인 경우 매너 온도 조정
	    if ((reportCount + 1) % 5 == 0) {
	        int deductionCount = (int) ((reportCount + 1) / 5); // 5의 배수 횟수 계산
	        double mannerTemperatureDelta = 0.5; // 감소량

	        // 매너 온도를 조정하는 로직
	        double updatedMannerTemperature = agent.getManners_temperature() - (mannerTemperatureDelta);
	        agent.setManners_temperature(updatedMannerTemperature);
	    }

	    // report_Count 증가
	    agent.setReport_Count(reportCount + 1);
	    aRepo.save(agent);
	}







	public void revoke_approval(Long seq) {
	    Report e = rRepo.findBySeq(seq);
	    ReportStatus reportStatus = new ReportStatus();
	    reportStatus.setId("rs1");
	    e.setReportStatus(reportStatus);
	    rRepo.save(e);
	    
	    // RealEstateAgent의 report_Count 감소 로직
	    RealEstateAgent agent = e.getRealEstateAgent();
	    Long reportCount = agent.getReport_Count();

	    if (reportCount > 0) {
	        agent.setReport_Count(reportCount - 1);
	        aRepo.save(agent);
	        
	        // 감소된 report_Count가 5의 배수일 때, manner_temperature 복구 로직
	        if (reportCount % 5 == 0) {
	            int deductionCount = (int) (reportCount / 5); // 5의 배수 횟수 계산
	            double mannerTemperatureDelta = 0.5; // 감소량
	            double updatedMannerTemperature = agent.getManners_temperature() + (mannerTemperatureDelta * deductionCount);
	            agent.setManners_temperature(updatedMannerTemperature);
	            aRepo.save(agent);
	        }
	    }
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
