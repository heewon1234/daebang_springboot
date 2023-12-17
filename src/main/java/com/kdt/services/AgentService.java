package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.domain.entities.Real_Estate_Agent;
import com.kdt.dto.MemberDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.UpdateEstateDTO;
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
	    RealEstateAgentDTO.setManners_temperature(36.5);
	    System.out.println("위도"+RealEstateAgentDTO.getLatitude());
	    Real_Estate_Agent e = aMapper.toEntity(RealEstateAgentDTO);
	    aRepo.save(e);
	}

	public boolean isEstateNumber(String number) {
	    Real_Estate_Agent e = aRepo.findByEstateNumber(number);
	    System.out.println(e);
	    return e != null; // 해당 estateNumber가 존재하면 true, 존재하지 않으면 false 반환
	}
	
	
	// 마이페이지 정보 띄우기
	public RealEstateAgentDTO estateInfo(String id) {
		Real_Estate_Agent a = aRepo.findById(id).get();
		RealEstateAgentDTO adto = aMapper.toDto(a);
		return adto;
	}
	
	// 공인중개사 비밀번호 변경
	@Transactional
	public void changePw(String id, String pw) {
		String hashedPassword = passwordEncoder.encode(pw);
		aRepo.changePw(id, hashedPassword);
	}
	
//	// 공인중개사 정보 변경
//	public void updateMyInfo(UpdateEstateDTO dto) {
//		Real_Estate_Agent a = aRepo.findById(dto.getId()).get();
//		RealEstateAgentDTO adto = new RealEstateAgentDTO(a.getEmail(),a.getPw(),a.getEstateName(),a.getEstateNumber(),dto.getName(),dto.getAddress(),dto.getPhone(),a.getManners_temperature(),a.getRole(),a.isEnabled());
//		aMapper.updateEntityFromDTO(adto, a);
//		aRepo.save(a);
//	}
}
