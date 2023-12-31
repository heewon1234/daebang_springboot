package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.dto.AgentProfileDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.UpdateEstateDTO;
import com.kdt.dto.UploadEstateDTO;
import com.kdt.dto.UploadEstateOptionDTO;
import com.kdt.services.AgentService;

@RestController
@RequestMapping("/api/estate/")
public class EstateAgentController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstateAgentController.class);

	@Autowired
	private AgentService aServ;
	
	@GetMapping("getEnabled/{id}")
	public ResponseEntity<Boolean> getEnabled(@PathVariable String id) {
		Boolean enabled = aServ.getEnabled(id);
		return ResponseEntity.ok(enabled);
	}

	@GetMapping("estateInfo/{id}")
	public ResponseEntity<RealEstateAgentDTO> estateInfo(@PathVariable String id) {
		try {
			RealEstateAgentDTO dto = aServ.estateInfo(id);
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("changePw")
	public ResponseEntity<Void> changePw(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		aServ.changePw(id, pw);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		aServ.deleteById(id);
		return ResponseEntity.ok().build();
	}
	@GetMapping("getname/{estateid}")
	public ResponseEntity<String> getname(@PathVariable String estateid){
		String name = aServ.getNamebyId(estateid);
		return ResponseEntity.ok(name);
	}
	@PostMapping("updateMyInfo")
	public ResponseEntity<Void> updateMyInfo(@RequestBody UpdateEstateDTO dto) {
		aServ.updateMyInfo(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("findId/{name}/{phone}")
	public ResponseEntity<List<String>> getId(@PathVariable String name, @PathVariable String phone) {
		List<RealEstateAgentDTO> dto = aServ.getId(name, phone);
		List<String> idList = new ArrayList<>();
		if (!(dto.size() == 0)) {
			for (int i = 0; i < dto.size(); i++) {
				idList.add(dto.get(i).getEmail());
			}
		}
		return ResponseEntity.ok(idList);

	}

	@PostMapping("profileImage/{userId}")
	public ResponseEntity<Void> updateById(@PathVariable String userId,
			@RequestParam(value = "profileImage", required = false) List<MultipartFile> profileImage) {

		if (profileImage == null) {
			return ResponseEntity.ok().build();
		} else {
			aServ.insertImage(userId, profileImage);

			return ResponseEntity.ok().build();
		}
	}

	@GetMapping("profileImage/{userId}")
	public ResponseEntity<List<AgentProfileDTO>> getImageById(@PathVariable String userId) {
		List<AgentProfileDTO> apList = aServ.getImageById(userId);
		
		if (apList != null) {
			return ResponseEntity.ok(apList);
		} else {
			return ResponseEntity.ok(null);
		}

	}
}
