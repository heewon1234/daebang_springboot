package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.EstateDTO;
import com.kdt.dto.MapRegionDTO;
import com.kdt.services.EstateService;
import com.kdt.services.MapService;

@RestController
@RequestMapping("/api/map/")
public class MapController {

	@Autowired
	private MapService mServ;

	@Autowired
	private EstateService eServ;

	// 첫 로딩시 모든 매물 정보 가져오기
	@GetMapping("getAll")
	public ResponseEntity<List<EstateDTO>> getAll() {
		try {
			List<EstateDTO> list = eServ.selectAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 키워드 검색
	@GetMapping("getKeywordSearch")
	public ResponseEntity<List<MapRegionDTO>> getKeywordSearch(@RequestParam("keyword") String keyword) {
		try {
			List<MapRegionDTO> regionList = mServ.selectRegion(keyword);
			return ResponseEntity.ok(regionList);

		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
