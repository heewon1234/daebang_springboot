package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MemberDTO;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/member/")
public class MemberController {

	@Autowired
	private MemberService mServ;
	
	@PostMapping("idDuplCheck")
	public ResponseEntity<Boolean> idDuplCheck(@RequestBody MemberDTO dto) {
		Boolean isdupl = mServ.idDuplCheck(dto);
		return ResponseEntity.ok(isdupl);
	}
	
	@PostMapping("signUp")
	public ResponseEntity<Void> login(@RequestBody MemberDTO dto) {
		mServ.signUp(dto);
		return ResponseEntity.ok().build();
	}
}
