package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.EstateDTO;
import com.kdt.dto.ReviewApprovalDTO;
import com.kdt.services.EstateService;
import com.kdt.services.ReviewApprovalService;

@RestController
@RequestMapping("/api/reviewApproval/")
public class ReviewApprovalController {

	@Autowired
	private ReviewApprovalService rServ;

	@Autowired
	private EstateService eServ;

	@GetMapping("myReview/{id}")
	public ResponseEntity<List<ReviewApprovalDTO>> selectAll(@PathVariable String id) {
		List<ReviewApprovalDTO> list = rServ.selectAll(id);
		return ResponseEntity.ok(list);
	}

	@GetMapping("agentReview/{loginId}")
	public ResponseEntity<List<ReviewApprovalDTO>> selectById(@PathVariable String loginId) {
		List<ReviewApprovalDTO> list = rServ.selectByAgent(loginId);

		return ResponseEntity.ok(list);
	}

	@GetMapping("estate/{id}")
	public ResponseEntity<EstateDTO> selectEstate(@PathVariable Long id) {
		EstateDTO dto = eServ.selectEstate(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ReviewApprovalDTO reviewApprovalDTO) {
//		System.out.println(reviewApprovalDTO.getEstateCode());
		System.out.println(reviewApprovalDTO.getEstate().getEstateId());//확인해보셈
		System.out.println(reviewApprovalDTO.getUserId());

		rServ.insert(reviewApprovalDTO);

		return ResponseEntity.ok().build();
	}

	@PutMapping("updateStatus/{seq}")
	public ResponseEntity<Void> updateStatus(@PathVariable Long seq, @RequestBody ReviewApprovalDTO dto) {
		dto.setSeq(seq);

		rServ.updateStatus(dto);

		return ResponseEntity.ok().build();
	}
	
//	@GetMapping("sawEstate/{id}")
//	public ResponseEntity<List<SawEstateDTO>> selectSawEstate(@PathVariable String id) {
//		List<SawEstateDTO> list = rServ.selectSawEstate(id);
//		return ResponseEntity.ok(list);
//	}
	//관리자 승인
	@GetMapping("admin/selectByAdmin")
	public ResponseEntity<List<ReviewApprovalDTO>> selectByAdmin() {
		List<ReviewApprovalDTO> list = rServ.selectByAdmin();
		System.out.println(list.get(0).getEstateName());
		return ResponseEntity.ok(list);
	}
	@PutMapping("admin/revoke-approval/{seq}")
	public ResponseEntity<Void> revoke(@PathVariable Long seq) {
		rServ.revoke_approval(seq);
		System.out.println("번호"+seq);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("admin/approval/{seq}")
	public ResponseEntity<Void> approval(@PathVariable Long seq) {
		rServ.approval(seq);
		
		return ResponseEntity.ok().build();
	}
	@PutMapping("admin/return/{seq}")
	public ResponseEntity<Void> back(@PathVariable Long seq) {
		rServ.back(seq);
		System.out.println("번호리턴"+seq);
		return ResponseEntity.ok().build();
	}
}
