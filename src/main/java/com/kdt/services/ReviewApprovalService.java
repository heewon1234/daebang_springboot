package com.kdt.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.ReviewApproval;
import com.kdt.domain.entities.SawEstate;
import com.kdt.domain.entities.UploadEstate;
import com.kdt.dto.ReviewApprovalDTO;
import com.kdt.dto.SawEstateDTO;
import com.kdt.mappers.ReviewApprovalMapper;
import com.kdt.mappers.SawEstateMapper;
import com.kdt.repositories.ReviewApprovalRepository;
import com.kdt.repositories.SawEstateRepository;
import com.kdt.repositories.UploadEstateRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewApprovalService {

	@Autowired
	private ReviewApprovalMapper rMapper;
	
	@Autowired
	private SawEstateMapper sMapper;

	@Autowired
	private ReviewApprovalRepository rRepo;
	@Autowired
	private UploadEstateRepository ueRepo;
	
	@Autowired
	private SawEstateRepository sRepo;
	
	

	public List<ReviewApprovalDTO> selectAll(String loginId) {
		List<ReviewApproval> rList = rRepo.findAllByUserId(loginId);
		List<ReviewApprovalDTO> list = rMapper.toDtoList(rList);
		return list;
	}

	public List<ReviewApprovalDTO> selectByAgent(String loginId) {
		List<UploadEstate> estateList = ueRepo.findByWriter(loginId);
		List<ReviewApproval> rList = new ArrayList<>();

		for (UploadEstate estate : estateList) {
			System.out.println("estateCode" + estate.getEstateId());
			ReviewApproval result = rRepo.findByApprovalCodeAndEstateCode("a1", estate.getEstateId());
			if (result != null) {
				rList.add(result);
			}
		}

		List<ReviewApprovalDTO> list = rMapper.toDtoList(rList);

		return list;
	}
	
	public void insert(ReviewApprovalDTO dto) {
		ReviewApproval ra = rMapper.toEntity(dto);
		
		ra.setApprovalCode("a1");
		ra.setWriteDate(new Timestamp(System.currentTimeMillis()));
		
		rRepo.save(ra);
	}
	
	@Transactional
	public void updateStatus(ReviewApprovalDTO dto) {
		ReviewApproval ra = rMapper.toEntity(dto);
		
		System.out.println(ra.getSeq());
		System.out.println(ra.getApprovalCode());
		
		rRepo.updateStatue(ra.getSeq(), ra.getApprovalCode());
		
	}
	
	public List<SawEstateDTO> selectSawEstate(String id) {
		List<SawEstate> list = sRepo.selectSawEstate(id);
		List<SawEstateDTO> dtos = sMapper.toDtoList(list);
		return dtos;
	}
}
