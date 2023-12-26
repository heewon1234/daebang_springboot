package com.kdt.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.NewEstate;
import com.kdt.dto.NewEstateDTO;
import com.kdt.mappers.NewEstateMapper;
import com.kdt.repositories.NewEstateRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NewEstateService {
	@Autowired
	private NewEstateRepository nRepo;
	@Autowired
	private NewEstateMapper nMapper;
	
	public NewEstateDTO getTodayNewEstate() {
		LocalDate today = LocalDate.now();
		NewEstate v =  nRepo.findByEstateDate(today);
		NewEstateDTO dto = nMapper.toDto(v);
		return dto;
	}

	public void createNewEstate() {
		NewEstate newEstate = new NewEstate();
		newEstate.setEstateDate(LocalDate.now());
		newEstate.setEstateCount(1);
		nRepo.save(newEstate);
	}

	public void incrementNewEstate(Long seq) {
		NewEstate todayNewMember = nRepo.findById(seq).get();
		todayNewMember.setEstateCount(todayNewMember.getEstateCount()+1);
		nRepo.save(todayNewMember);
	}
	public List<NewEstateDTO> getAll() {
		List<NewEstate> list = nRepo.findAll();
		List<NewEstateDTO> dlist = nMapper.toDtoList(list);
		return dlist;
	}
	
	public int newEstate_sum() {
		return nRepo.sumEstateCount();
	}


	public NewEstate getDailyNewEstate(LocalDate date) {
		return nRepo.findByEstateDate(date);
	}
	public NewEstate getYesterdayNewEstate(LocalDate date) {
		return nRepo.findByEstateDate(date);
	}
}
