package com.kdt.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.ReviewApproval;
import com.kdt.domain.entities.SawEstate;
import com.kdt.domain.entities.UploadEstate;
import com.kdt.domain.entities.UploadReviewApproval;
import com.kdt.dto.ReviewApprovalDTO;
import com.kdt.dto.SawEstateDTO;
import com.kdt.dto.UploadReviewApprovalDTO;
import com.kdt.mappers.ReviewApprovalMapper;
import com.kdt.mappers.SawEstateMapper;
import com.kdt.mappers.UploadReviewApprovalMapper;
import com.kdt.repositories.ReviewApprovalRepository;
import com.kdt.repositories.SawEstateRepository;
import com.kdt.repositories.UploadEstateRepository;
import com.kdt.repositories.UploadReviewApprovalRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewApprovalService {

	@Autowired
	private ReviewApprovalMapper rMapper;
	@Autowired
	private UploadReviewApprovalMapper uraMapper;

	@Autowired
	private SawEstateMapper sMapper;

	@Autowired
	private ReviewApprovalRepository rRepo;
	@Autowired
	private UploadEstateRepository ueRepo;
	@Autowired
	private UploadReviewApprovalRepository uraRepo;

	@Autowired
	private SawEstateRepository sRepo;

	public List<ReviewApprovalDTO> selectAll(String loginId) {
		List<ReviewApproval> rList = rRepo.findAllByUserId(loginId);
		List<ReviewApprovalDTO> list = rMapper.toDtoList(rList);
		return list;
	}

	public List<UploadReviewApprovalDTO> selectByAgent(String loginId) {
		List<UploadEstate> estateList = ueRepo.findByWriter(loginId);
		List<String> approvalCodes = Arrays.asList("a1", "a2", "b1");

		List<UploadReviewApproval> rList = new ArrayList<>();

		for (UploadEstate estate : estateList) {
			UploadReviewApproval result = uraRepo.findByEstateCodeAndApprovalCodeIn(estate.getEstateId(), approvalCodes);
			if (result != null) {
				rList.add(result);
			} 
		}

		List<UploadReviewApprovalDTO> list = uraMapper.toDtoList(rList);

		return list;
	}

	public void insert(UploadReviewApprovalDTO dto) {
		UploadReviewApproval ura = uraMapper.toEntity(dto);

		ura.setApprovalCode("a1");
		ura.setWriteDate(new Timestamp(System.currentTimeMillis()));

		uraRepo.save(ura);
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

	// 관리자 페이지
	public List<ReviewApprovalDTO> selectByAdmin() {
		List<String> approvalCodes = Arrays.asList("a2", "a3", "a4", "b1");
		List<ReviewApproval> rList = rRepo.findAllByApprovalCodeIn(approvalCodes);
		List<ReviewApprovalDTO> list = rMapper.toDtoList(rList);
		return list;
	}

	public void approval(Long seq) {
		ReviewApproval e = rRepo.findById(seq).get();
		e.setApprovalCode("a3"); // Assuming "setApprovalCode" is the setter method for approvalCode
		rRepo.save(e);
	}

	public void revoke_approval(Long seq) {
		ReviewApproval e = rRepo.findById(seq).get();
		e.setApprovalCode("a2");
		rRepo.save(e);
	}

	public void back(Long seq) {
		ReviewApproval e = rRepo.findById(seq).get();
		e.setApprovalCode("b1");
		rRepo.save(e);
	}

	public void finalBack(Long seq) {
		ReviewApproval e = rRepo.findById(seq).get();
		e.setApprovalCode("a4");
		rRepo.save(e);
	}
}
