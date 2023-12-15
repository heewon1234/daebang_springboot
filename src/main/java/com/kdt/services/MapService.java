package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.MapRegion;
import com.kdt.dto.MapRegionDTO;
import com.kdt.dto.MemberDTO;
import com.kdt.mappers.MapRegionMapper;
import com.kdt.mappers.MapSchoolMapper;
import com.kdt.mappers.MapSubwayMapper;
import com.kdt.repositories.MapRegionRepository;

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

	public List<MapRegionDTO> selectRegion(String keyword) {
		List<MapRegion> list = mRegionRepo.selectByKeyword(keyword);
		List<MapRegionDTO> dtos = mRegionMapper.toDtoList(list);
		return dtos;

		// List<Member> list = mRegionRepo.findByEupMyeonDong(keyword);
		// List<Member> list = mRegionRepo.findByRe(keyword);

	}

	public List<MapRegionDTO> selectSubway(String keyword) {

		return null;
	}

}
