package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.RealEstateViews;
import com.kdt.dto.RealEstateViewsDTO;
import com.kdt.mappers.RealEstateViewsMapper;
import com.kdt.repositories.RealEstateViewsRepository;

@Service
public class RealEstateViewsService {
	@Autowired
	private RealEstateViewsRepository rRepo;
	
	@Autowired
	private RealEstateViewsMapper rMapper;
	
	public List<RealEstateViewsDTO> topFive() {
		List<RealEstateViews> eList = rRepo.topFive();
		List<RealEstateViewsDTO> list = rMapper.toDtoList(eList);
		return list;
	}
}
