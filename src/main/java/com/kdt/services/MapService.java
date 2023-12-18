package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.MapRegion;
import com.kdt.domain.entities.MapSubway;
import com.kdt.dto.MapRegionDTO;
import com.kdt.dto.MapSubwayDTO;
import com.kdt.mappers.MapRegionMapper;
import com.kdt.mappers.MapSchoolMapper;
import com.kdt.mappers.MapSubwayMapper;
import com.kdt.repositories.MapRegionRepository;
import com.kdt.repositories.MapSubwayRepository;

@Service
public class MapService {

	@Autowired
	private MapRegionMapper mRegionMapper;

	@Autowired
	private MapSchoolMapper mSchoolMapper;

	@Autowired
	private MapSubwayMapper mSubwayMapper;

	@Autowired
	private MapRegionRepository mRegionRepo;
	
	@Autowired
	private MapSubwayRepository mSubwayRepo;

	
	// 지역 정보 받아오기
	public List<MapRegionDTO> selectRegion(String keyword) {
		List<MapRegion> list = mRegionRepo.selectByKeyword(keyword);
		List<MapRegionDTO> dtos = mRegionMapper.toDtoList(list);
		return dtos;
	}

	// 지하철 정보 받아오기
	public List<MapSubwayDTO> selectSubway(String keyword) {
		List<MapSubway> list = mSubwayRepo.findByNameStartingWith(keyword);
		List<MapSubwayDTO> dtos = mSubwayMapper.toDtoList(list);
		return dtos;
	}

}
