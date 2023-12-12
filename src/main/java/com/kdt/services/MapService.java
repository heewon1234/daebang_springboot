package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Estate;
import com.kdt.dto.EstateDTO;
import com.kdt.mappers.EstateMapper;
import com.kdt.repositories.MapRepository;

@Service
public class MapService {
	
	@Autowired
	private MapRepository mRepo;
	
	@Autowired
	private EstateMapper mMapper;
	
	public List<EstateDTO> getAll(){
		List<Estate> list = mRepo.findAll();
		List<EstateDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}

}
