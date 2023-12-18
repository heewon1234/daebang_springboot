package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.ReviewDTO;
import com.kdt.dto.UploadReviewDTO;
import com.kdt.security.SecurityUser;
import com.kdt.services.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	private SecurityUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null && auth.getPrincipal() instanceof SecurityUser) {
			SecurityUser su = (SecurityUser)auth.getPrincipal();
			return su;
		}
		return null;
	}

	@Autowired
	ReviewService rServ;

	@PostMapping
	public ResponseEntity<Void> insertReview(UploadReviewDTO dto) throws Exception{
		dto.setId(getUser().getUsername());
		rServ.insertReview(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{estateId}")
	public ResponseEntity<List<ReviewDTO>> selectReviewByEstateId(@PathVariable Long estateId) throws Exception{
		List<ReviewDTO> list = rServ.selectReviewByEstateId(estateId);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/selectReviewBySeq/{seq}")
	public ResponseEntity<ReviewDTO> selectReviewBySeq(@PathVariable Long seq){
		ReviewDTO dto = rServ.selectReviewBySeq(seq);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> delReviewBySeq(@PathVariable Long seq){
		rServ.delReviewBySeq(seq);
		return ResponseEntity.ok().build();
	}

	// 리뷰 수정
	@PutMapping("/{seq}")
	public ResponseEntity<Void> updateReview(@PathVariable Long seq, UploadReviewDTO dto, String[] delFileList) throws Exception{
		dto.setSeq(seq);
		dto.setId(getUser().getUsername());
		rServ.updateReview(dto, delFileList);
		return ResponseEntity.ok().build();
	}

}
