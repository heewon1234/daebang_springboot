package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.EstateDTO;
import com.kdt.dto.MapRegionDTO;
import com.kdt.dto.MapSchoolDTO;
import com.kdt.dto.MapSubwayDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.services.EstateService;
import com.kdt.services.MapService;

@RestController
@RequestMapping("/api/map/")
public class MapController {
	
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);

	@Autowired
	private MapService mServ;

	@Autowired
	private EstateService eServ;

	// 지도 로딩시 매물 정보 가져오기
	@GetMapping("getAll")
	public ResponseEntity<List<EstateDTO>> getAll() {
		try {
			List<EstateDTO> list = eServ.selectAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 지도 로딩시 정보 가져오기
	@GetMapping("getLimitAll")
	public ResponseEntity<List<EstateDTO>> getLimitAll() {
		try {
			List<EstateDTO> list = eServ.selectLimitAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// 지도 로딩시 지하철, 학교 정보 가져오기
	@GetMapping("getAllDefaultMaker")
	public ResponseEntity<Map<String, Object>> getAllDefaultMaker() {
		try {
			Map<String, Object> result = new HashMap<>();

			List<MapSubwayDTO> subwayList = mServ.selectAllSubway();
			List<MapSchoolDTO> schoolList = mServ.selectAllSchool();
			
			result.put("subwayList", subwayList);
			result.put("schoolList", schoolList);

			return ResponseEntity.ok(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// 키워드 검색
	@GetMapping("getKeywordSearch")
	public ResponseEntity<Map<String, Object>> getKeywordSearch(@RequestParam("keyword") String keyword) {
		try {
			Map<String, Object> result = new HashMap<>();

			List<MapRegionDTO> regionList = mServ.selectRegion(keyword);
			List<MapSubwayDTO> subwayList = mServ.selectSubway(keyword);
			List<MapSchoolDTO> schoolList = mServ.selectSchool(keyword);

			result.put("regionList", regionList);
			result.put("subwayList", subwayList);
			result.put("schoolList", schoolList);

			return ResponseEntity.ok(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("getWatchAll/{recent}")
	public ResponseEntity<List<EstateDTO>> getWatchAll(@PathVariable List<Long> recent) {
		try {
			List<EstateDTO> list = eServ.selectWatchAll(recent);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("getImageAll/{recent}")
	public ResponseEntity<List<String>> getImageAll(@PathVariable List<Long> recent) {
		try {
			List<String> list = eServ.selectImageAll(recent);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 작성자 매너온도 가져오기
	@GetMapping("callAgentState")
	public ResponseEntity<List<RealEstateAgentDTO>> callAgentState(@RequestParam("email") String email) {
	    System.out.println(email);

	    try {
	        // 이메일 문자열을 배열로 분리
	        String[] emailArray = email.split(",");

	        List<RealEstateAgentDTO> result = new ArrayList<>();

	        // 각 이메일 주소에 대해 조회
	        for (String individualEmail : emailArray) {
	            List<RealEstateAgentDTO> agentDtos = mServ.callAgentState(individualEmail);
	            result.addAll(agentDtos); // 리스트에 추가
	        }

	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	// 공인 중개사의 최신 게시물 10개
	@GetMapping("getAgentContentLimit")
	public ResponseEntity<List<EstateDTO>> getAgentContentLimit(@RequestParam("email") String email) {

	    try {
	        List<EstateDTO> estateListLimit = mServ.getAgentContentLimit(email);
	        return ResponseEntity.ok(estateListLimit);
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	// 신고하기
	@PostMapping("report")
	public ResponseEntity<ReportDTO> report(@RequestBody ReportDTO reportDTO) {
		try {
			mServ.report(reportDTO);
		    return ResponseEntity.ok().build();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
