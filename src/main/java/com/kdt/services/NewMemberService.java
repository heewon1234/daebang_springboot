package com.kdt.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.NewMember;
import com.kdt.domain.entities.Visitor;
import com.kdt.dto.NewMemberDTO;
import com.kdt.dto.VisitorDTO;
import com.kdt.mappers.NewMemberMapper;
import com.kdt.repositories.NewMemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NewMemberService {
	@Autowired
	private NewMemberRepository nRepo;
	@Autowired
	private NewMemberMapper nMapper;
	
	public NewMemberDTO getTodayNewMamber() {
		LocalDate today = LocalDate.now();
		NewMember v =  nRepo.findByNewMemberDate(today);
		NewMemberDTO dto = nMapper.toDto(v);
		return dto;
	}

	public void createNewMamber() {
		NewMember newMember = new NewMember();
		newMember.setNewMemberDate(LocalDate.now());
		newMember.setNewMemberCount(1);
		nRepo.save(newMember);
	}

	public void incrementNewMember(Long seq) {
		NewMember todayNewMember = nRepo.findById(seq).get();
		todayNewMember.setNewMemberCount(todayNewMember.getNewMemberCount()+1);
		nRepo.save(todayNewMember);
	}
	public List<NewMemberDTO> getAll() {
		List<NewMember> list = nRepo.findAll();
		List<NewMemberDTO> dlist = nMapper.toDtoList(list);
		return dlist;
	}
//	public int sum() {
//		return vRepo.sumVisitorCount();
//	}
//
//
//	public Visitor getDailyVisitors(LocalDate date) {
//		return vRepo.findByVisitorDate(date);
//	}
}
