package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.ReplyDTO;
import com.kdt.security.SecurityUser;
import com.kdt.services.ReplyService;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

	@Autowired
	private ReplyService rServ;
	
	private SecurityUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null && auth.getPrincipal() instanceof SecurityUser) {
			SecurityUser su = (SecurityUser)auth.getPrincipal();
			return su;
		}
		return null;
	}
	
	// 댓글 삽입
	@PostMapping
	public ResponseEntity<List<ReplyDTO>> insertReply(@RequestBody ReplyDTO dto){
		dto.setWriter(getUser().getUsername());
		List<ReplyDTO> list = rServ.insertReply(dto);
		return ResponseEntity.ok(list);
	}
	
	// 댓글 수정
	@PutMapping
	public ResponseEntity<List<ReplyDTO>> updateReply(@RequestBody ReplyDTO dto){
		List<ReplyDTO> list = rServ.updateReply(dto);
		return ResponseEntity.ok(list);
	}
	
	// 댓글 삭제
	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> delReply(@PathVariable Long seq){
		rServ.delReply(seq);
		return ResponseEntity.ok().build();
	}
	
}
