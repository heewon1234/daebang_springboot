package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<MemberDTO> getAllMembers() {
		List<Member> list = mRepo.findAll();
		return mMapper.toDtoList(list);
	}
}
