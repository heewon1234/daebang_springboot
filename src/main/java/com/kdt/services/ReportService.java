package com.kdt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Report;
import com.kdt.dto.ReportDTO;
import com.kdt.mappers.ReportMapper;
import com.kdt.repositories.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository rRepo;
	
	@Autowired
	private ReportMapper rMapper;
	
	public List<ReportDTO> selectAllByReportStatus() {
		
		List<Report> list = rRepo.selectAllByReportStatus();
        List<ReportDTO> dtos = new ArrayList<>();

        for (Report report : list) {
            ReportDTO dto = new ReportDTO(report);
            dtos.add(dto);
        }

        // dtos에 있는 각각의 DTO 객체에서 필요한 값을 가져올 수 있음
        for (ReportDTO dto : dtos) {
        	dto.getSeq();
            dto.getEstate_id();
            dto.getTaker();
            dto.getContents_code();
            dto.getWriter();
            dto.getWriteDate();
            // 이 값을 사용할 수 있음
        }

        return dtos;
	}
	
	public Long countByReportStatus() {
		return rRepo.countByReportStatus();
	}
	
	public List<ReportDTO> selectAll() {
        List<Report> list = rRepo.findAll();
        List<ReportDTO> dtos = new ArrayList<>();

        for (Report report : list) {
            ReportDTO dto = new ReportDTO(report);
            dtos.add(dto);
        }

        // dtos에 있는 각각의 DTO 객체에서 필요한 값을 가져올 수 있음
        for (ReportDTO dto : dtos) {
        	dto.getSeq();
            dto.getEstate_id();
            dto.getTaker();
            dto.getContents_code();
            dto.getWriter();
            dto.getWriteDate();
            // 이 값을 사용할 수 있음
        }

        return dtos;
    }
//	public List<ReportDTO> selectAll(){
//		List<Report> list = rRepo.findAll();
//		System.out.println(list.get(0).getRealEstateAgent().getEstateName());
//		System.out.println(list.get(0).getReportStatus().getStatus());
//		System.out.println(list.get(0).getReportContents().getContent());
//		System.out.println(list.get(0).getEstate().getEstateId());
//		System.out.println(list.get(0).getWriter());
//		//결과임
//		//천안두정역대림공인중개사사무소
//		//대기중
//		//거래 완료된 매물입니다.
//		//7
//		//chenje
//		List<ReportDTO> dtos = rMapper.toDtoList(list);
//		return dtos;
//	}
}
