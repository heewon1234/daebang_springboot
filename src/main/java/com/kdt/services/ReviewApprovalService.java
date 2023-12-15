package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dto.ReviewApprovalDTO;
import com.kdt.mappers.ReviewApprovalMapper;
import com.kdt.repositories.ReviewApprovalRepository;

@Service
public class ReviewApprovalService {
	
	@Autowired
	private ReviewApprovalMapper rMapper;
	
	@Autowired
	private ReviewApprovalRepository rRepo;

	public List<ReviewApprovalDTO> selectAll(String loginId) {
		List<ReviewApprovalDTO> list = rRepo.findAllByUserId(loginId);
		return list;
	}
}
