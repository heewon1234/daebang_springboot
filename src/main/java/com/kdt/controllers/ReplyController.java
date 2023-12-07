package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	public SecurityUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null && auth.getPrincipal() instanceof SecurityUser) {
			SecurityUser su = (SecurityUser)auth.getPrincipal();
			return su;
		}
		return null;
	}
	
	@PostMapping
	public ResponseEntity<Void> insertReply(@RequestBody ReplyDTO dto){
		dto.setWriter(getUser().getUsername());
		rServ.insertReply(dto);
		return ResponseEntity.ok().build();
	}
	
}
