package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.EstateDTO;
import com.kdt.services.MapService;

@RestController
@RequestMapping("/api/map/")
public class MapController {
	
	@Autowired
	private MapService mServ;
	
	@GetMapping("getAll")
    public ResponseEntity<List<EstateDTO>> getAll() {
    	try {
    		List<EstateDTO> list = mServ.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
