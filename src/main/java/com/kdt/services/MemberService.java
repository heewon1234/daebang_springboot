package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kdt.config.SecurityConfig;
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
	
	public List<MemberDTO> getAllMembers() {
		List<Member> list = mRepo.findAll();
		return mMapper.toDtoList(list);
	}
	
	public void signUp(MemberDTO dto) {
		dto.setRole("ROLE_MEMBER");
		dto.setEnabled(true);
		String hashedPassword = passwordEncoder.encode(dto.getPw());
		dto.setPw(hashedPassword);
		Member m = mMapper.toEntity(dto);
		mRepo.save(m);
	}
}
