package com.kdt.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.EstateOption;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.EstateOptionDTO;
import com.kdt.mappers.EstateMapper;
import com.kdt.mappers.EstateOptionMapper;
import com.kdt.repositories.EstateOptionRepository;
import com.kdt.repositories.EstateRepository;

import jakarta.transaction.Transactional;

@Service
public class EstateService {

	@Autowired
	private EstateMapper eMapper;
	@Autowired
	private EstateOptionMapper eoMapper;
	
	@Autowired
	private EstateRepository eRepo;
	@Autowired
	private EstateOptionRepository eoRepo;
	
	@Transactional
	public void insertEstate(EstateDTO dto, List<EstateOptionDTO> optionDTOList) {
		// 매물 입력 ->
		Estate estate = eMapper.toEntity(dto);
		
		if(estate.getMaintenanceCost() == null) {
			estate.setMaintenanceCost(0L);
		}
		estate.setWriteDate(new Timestamp(System.currentTimeMillis()));
		estate = eRepo.save(estate);
		
		System.out.println(estate.getEstateId());
		// <- 매물 입력
		
		// 매물 옵션 입력 ->
		for(EstateOptionDTO optionDTO : optionDTOList) {
			EstateOption option = eoMapper.toEntity(optionDTO);
			option.setEstateCode(estate.getEstateId());
			
			eoRepo.save(option);
		}
		// <- 매물 옵션 입력
	}
}
