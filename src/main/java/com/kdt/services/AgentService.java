package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Real_Estate_Agent;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.mappers.AgentMapper;
import com.kdt.repositories.AgentRepository;

@Service
public class AgentService {
	@Autowired
	private AgentRepository aRepo;
	
	@Autowired
	private AgentMapper aMapper;
	
	public List<RealEstateAgentDTO> getAll(){
		List<Real_Estate_Agent> list = aRepo.findAll();
		List<RealEstateAgentDTO> dtos = aMapper.toDtoList(list);
		return dtos;
	}
	public void deleteById(String email) {
		Real_Estate_Agent e = aRepo.findById(email).get();
		aRepo.delete(e);
	}
	public void approve(String email) {
		Real_Estate_Agent e = aRepo.findById(email).get();
		e.setEnabled(true);
		aRepo.save(e);
	}
	public void revoke_approval(String email) {
		Real_Estate_Agent e = aRepo.findById(email).get();
		e.setEnabled(false);
		aRepo.save(e);
	}
}
