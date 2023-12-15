package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.services.AgentService;

@RestController
@RequestMapping("/api/estate/")
public class EstateAgentController {

	@Autowired
	private AgentService aServ;
	
	@GetMapping("estateInfo/{id}")
	public ResponseEntity<RealEstateAgentDTO> estateInfo(@PathVariable String id) {
		try {
			RealEstateAgentDTO dto = aServ.estateInfo(id);
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("changePw")
	public ResponseEntity<Void> changePw(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		aServ.changePw(id,pw);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		aServ.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
