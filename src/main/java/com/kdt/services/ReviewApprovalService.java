package com.kdt.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.ReviewApproval;
import com.kdt.domain.entities.UploadEstate;
import com.kdt.dto.ReviewApprovalDTO;
import com.kdt.mappers.EstateMapper;
import com.kdt.mappers.ReviewApprovalMapper;
import com.kdt.repositories.EstateRepository;
import com.kdt.repositories.ReviewApprovalRepository;
import com.kdt.repositories.UploadEstateRepository;

@Service
public class ReviewApprovalService {

	@Autowired
	private ReviewApprovalMapper rMapper;

	@Autowired
	private ReviewApprovalRepository rRepo;
	@Autowired
	private UploadEstateRepository ueRepo;

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
}
