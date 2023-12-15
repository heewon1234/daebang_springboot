package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Real_Estate_Agent;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.mappers.AgentMapper;
import com.kdt.mappers.NewEstateMapper;
import com.kdt.repositories.AgentRepository;
import com.kdt.repositories.NewEstateRepository;

@Service
public class AgentService {
	@Autowired
	private AgentRepository aRepo;
	
	@Autowired
	private AgentMapper aMapper;
	
	@Autowired
	private NewEstateRepository nRepo;
	
	@Autowired
	private NewEstateMapper nMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	//@Autowired
    public AgentService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
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
	public void signup(RealEstateAgentDTO RealEstateAgentDTO) {
		String crypPw = passwordEncoder.encode(RealEstateAgentDTO.getPw());
		RealEstateAgentDTO.setPw(crypPw);
		RealEstateAgentDTO.setAddress("천안시");//나중에 지움
		RealEstateAgentDTO.setManners_temperature(36.5);
		Real_Estate_Agent e = aMapper.toEntity(RealEstateAgentDTO);
		aRepo.save(e);
	}
	public boolean isEstateNumber(String number) {
	    Real_Estate_Agent e = aRepo.findByEstateNumber(number);
	    System.out.println(e);
	    return e != null; // 해당 estateNumber가 존재하면 true, 존재하지 않으면 false 반환
	}
}
