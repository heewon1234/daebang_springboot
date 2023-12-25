package com.kdt.services;

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
	
	public List<ReportDTO> selectAll(){
		List<Report> list = rRepo.findAll();
		System.out.println(list.get(0).getRealEstateAgent().getEstateName());
		System.out.println(list.get(0).getReportStatus().getStatus());
		System.out.println(list.get(0).getReportContents().getContent());
		System.out.println(list.get(0).getEstate().getEstateId());
		System.out.println(list.get(0).getWriter());
		//결과임
		//천안두정역대림공인중개사사무소
		//대기중
		//거래 완료된 매물입니다.
		//7
		//chenje
		List<ReportDTO> dtos = rMapper.toDtoList(list);
		return dtos;
	}
}
