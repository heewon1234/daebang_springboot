package com.kdt.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.MapRegion;
import com.kdt.domain.entities.MapSchool;
import com.kdt.domain.entities.MapSubway;
import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.domain.entities.Report;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.MapRegionDTO;
import com.kdt.dto.MapSchoolDTO;
import com.kdt.dto.MapSubwayDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.mappers.AgentMapper;
import com.kdt.mappers.EstateMapper;
import com.kdt.mappers.MapRegionMapper;
import com.kdt.mappers.MapSchoolMapper;
import com.kdt.mappers.MapSubwayMapper;
import com.kdt.repositories.AgentRepository;
import com.kdt.repositories.EstateRepository;
import com.kdt.repositories.MapRegionRepository;
import com.kdt.repositories.MapSchoolRepository;
import com.kdt.repositories.MapSubwayRepository;
import com.kdt.repositories.ReportRepository;

@Service
public class MapService {

	@Autowired
	private MapRegionMapper mRegionMapper;

	@Autowired
	private MapSchoolMapper mSchoolMapper;

	@Autowired
	private MapSubwayMapper mSubwayMapper;

	@Autowired
	private AgentMapper agentMapper;

	@Autowired
	private EstateMapper eMapper;

	@Autowired
	private MapRegionRepository mRegionRepo;

	@Autowired
	private MapSubwayRepository mSubwayRepo;

	@Autowired
	private MapSchoolRepository mSchoolRepo;

	@Autowired
	private AgentRepository agentRepo;

	@Autowired
	private EstateRepository eRepo;
	
	@Autowired
	private ReportRepository rRepository;

	// 지역 정보 받아오기
	public List<MapRegionDTO> selectRegion(String keyword) {
		List<MapRegion> list = mRegionRepo.selectByKeyword(keyword);
		List<MapRegionDTO> dtos = mRegionMapper.toDtoList(list);
		return dtos;
	}

	// 지하철 정보 받아오기 (검색)
	public List<MapSubwayDTO> selectSubway(String keyword) {
		List<MapSubway> list = mSubwayRepo.findByNameStartingWith(keyword);
		List<MapSubwayDTO> dtos = mSubwayMapper.toDtoList(list);
		return dtos;
	}

	// 지하철 정보 가져오기 (전부 가져오기)
	public List<MapSubwayDTO> selectAllSubway() {
		List<MapSubway> list = mSubwayRepo.findAll();
		List<MapSubwayDTO> dtos = mSubwayMapper.toDtoList(list);
		return dtos;
	}

	// 학교 정보 받아오기 (검색)
	public List<MapSchoolDTO> selectSchool(String keyword) {
		List<MapSchool> list = mSchoolRepo.findByNameStartingWith(keyword);
		List<MapSchoolDTO> dtos = mSchoolMapper.toDtoList(list);
		return dtos;
	}

	// 학교 정보 받아오기 (전부 가져오기)
	public List<MapSchoolDTO> selectAllSchool() {
		List<MapSchool> list = mSchoolRepo.findAll();
		List<MapSchoolDTO> dtos = mSchoolMapper.toDtoList(list);
		return dtos;
	}

	// 매너온도 받아오기
	public List<RealEstateAgentDTO> callAgentState(String email) {
		List<RealEstateAgent> list = agentRepo.findAllByEmail(email);
		List<RealEstateAgentDTO> dtos = agentMapper.toDtoList(list);
		return dtos;
	}

	// 공인 중개사 최근 게시물 10개 가져오기
	public List<EstateDTO> getAgentContentLimit(String email) {
		Pageable pageable = PageRequest.of(0, 10); // 최신 10개 항목만 조회
		List<Estate> list = eRepo.findByRealEstateAgentEmailOrderByWriteDateDesc(email, pageable);
		List<EstateDTO> dtos = eMapper.toDtoList(list);
		return dtos;
	}
	
	// 신고하기
	public void report(ReportDTO reportDTO) {
		Report report = new Report();
		
		Date currentDate = new Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());

		report.setWriter(reportDTO.getWriter());
		report.setTaker(reportDTO.getTaker());
        report.setContent(reportDTO.getContent());
        report.setEstateId(reportDTO.getEstate_id());
        report.setContentsCode(reportDTO.getContents_code());
        report.setStatus_code("rs1");
        report.setWriteDate(timestamp);
        
        rRepository.save(report);
		
	}

}
