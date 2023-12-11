package com.kdt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Member;
import com.kdt.dto.MemberDTO;
import com.kdt.mappers.MemberMapper;
import com.kdt.repositories.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository mRepo;
	
	@Autowired
	private MemberMapper mMapper;
	
	private final PasswordEncoder passwordEncoder;

    //@Autowired
    public MemberService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public Boolean idDuplCheck(MemberDTO dto) {
		Optional<Member> m = mRepo.findById(dto.getId());
		if (m.isPresent()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void signUp(MemberDTO dto) {
		dto.setRole("ROLE_MEMBER");
		dto.setEnabled(true);
		String hashedPassword = passwordEncoder.encode(dto.getPw());
		dto.setPw(hashedPassword);
		Member m = mMapper.toEntity(dto);
		mRepo.save(m);
	}
	public List<MemberDTO> getAll(){
		List<Member> list = mRepo.findAll();
		List<MemberDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
	public void deleteById(String id) {
		Member e = mRepo.findById(id).get();
		mRepo.delete(e);
	}
	public List<MemberDTO> getId(String email){
		List<Member> list = mRepo.selectbyemail(email);
		List<MemberDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
	
	public MemberDTO myInfo(String id) {
		Member m = mRepo.findById(id).get();
		MemberDTO mdto = mMapper.toDto(m);
		return mdto;
	}
	
	public Boolean realPw(String id, String pw) {
		System.out.println("아이디: " + id);
		System.out.println("비번: " + pw);
		String hashedPassword = passwordEncoder.encode(pw);
		System.out.println("해시비번: " + hashedPassword);
		Member m = mRepo.checkidpw(id,hashedPassword);
		System.out.println(m);
		if(m != null) {
			return true;
		} else {
			return false;
		}
	}
}
