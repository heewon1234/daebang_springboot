package com.kdt.services;

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
}
