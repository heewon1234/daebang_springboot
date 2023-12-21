package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MemberDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.services.AgentService;
import com.kdt.services.MailService;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/mail")
public class MailController {

	@Autowired
	private MailService mailServ;
	
	@Autowired
	private MemberService mServ;
	
	@Autowired
	private AgentService aServ;
	
	@PostMapping("/findPw")
	public ResponseEntity<Integer> mailConfirm(@RequestParam("id") String id,@RequestParam("email") String email) throws Exception {
		System.out.println("클라이언트로 받은 email"+email+id);
		List<MemberDTO> dto = mServ.getDto(id, email);
		if(dto.size()==0) {
			return ResponseEntity.ok(dto.size());
		}
		else {
			String code = mailServ.sendSimpleMessage(email);
			System.out.println("인증코드 : " + code);
			mServ.changePw(id,code);
			return ResponseEntity.ok(dto.size());
		}
	}
	@PostMapping("/findenrollmentPw")
	public ResponseEntity<Integer> mailConfirm(@RequestParam("name") String name,@RequestParam("phone") String phone,@RequestParam("email") String email) throws Exception {
		List<RealEstateAgentDTO> dto = aServ.getDto(name,phone,email);
		if(dto.size()==0) {
			return ResponseEntity.ok(dto.size());
		}
		else {
			String code = mailServ.sendSimpleMessage(email);
			System.out.println("인증코드 : " + code);
			System.out.println(dto.get(0).getEmail());
			System.out.println(dto.get(0).getPw());
			aServ.changePw(email,code);
			return ResponseEntity.ok(dto.size());
		}
	}
}
