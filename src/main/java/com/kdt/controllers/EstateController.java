package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.UploadEstateDTO;
import com.kdt.dto.UploadEstateOptionDTO;
import com.kdt.services.EstateService;

@RestController
@RequestMapping("/api/estateManage/")
public class EstateController {

	@Autowired
	private EstateService eServ;

	@PostMapping
	public ResponseEntity<Void> insert(@RequestParam("realEstate") String realEstateJson,
			@RequestParam("optionList") String optionListJson,
			@RequestParam("estateImages") List<MultipartFile> estateImages) {

		List<UploadEstateOptionDTO> optionDTOList = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// realEstateJson 문자열을 UploadEstateDTO 객체로 변환
			UploadEstateDTO estateDTO = objectMapper.readValue(realEstateJson, UploadEstateDTO.class);

			// optionListJson를 파싱하여 EstateOptionDTO 객체 리스트로 변환
			String[] optionArray = objectMapper.readValue(optionListJson, String[].class);
			for (String optionCode : optionArray) {
				optionDTOList.add(new UploadEstateOptionDTO(null, null, optionCode));
			}

			eServ.insertEstate(estateDTO, optionDTOList, estateImages);

			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<EstateDTO>> selectAll() {
		List<EstateDTO> list = eServ.selectAll();

		return ResponseEntity.ok(list);
	}

	@GetMapping("estateBoard")
	public ResponseEntity<List<EstateDTO>> selectAll(@RequestParam String loginId) {
		List<EstateDTO> list = eServ.selectById(loginId);
		return ResponseEntity.ok(list);
	}

//	@GetMapping("estateUpdate/{estateId}")
//	public ResponseEntity<UploadEstateDTO> selectById(@PathVariable String estateId) {
//		UploadEstateDTO dto = eServ.selectById(Long.parseLong(estateId));
//
//		return ResponseEntity.ok(dto);
//	}

	@GetMapping("estateInfo/{estateId}")
	public ResponseEntity<EstateDTO> getById(@PathVariable String estateId) {
		EstateDTO dto = eServ.getById(Long.parseLong(estateId));

		return ResponseEntity.ok(dto);
	}

	@PutMapping("/estateUpdate/{estateId}")
	public ResponseEntity<Void> updateById(@PathVariable String estateId,
			@RequestParam("realEstate") String realEstateJson, 
			@RequestParam("optionList") String optionListJson,
			@RequestParam(value = "estateImages", required = false) List<MultipartFile> estateImages) {
		
		List<UploadEstateOptionDTO> optionDTOList = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// realEstateJson 문자열을 UploadEstateDTO 객체로 변환
			UploadEstateDTO estateDTO = objectMapper.readValue(realEstateJson, UploadEstateDTO.class);

			// optionListJson를 파싱하여 EstateOptionDTO 객체 리스트로 변환
			String[] optionArray = objectMapper.readValue(optionListJson, String[].class);
			for (String optionCode : optionArray) {
				optionDTOList.add(new UploadEstateOptionDTO(null, null, optionCode));
			}

			estateDTO.setEstateId(Long.parseLong(estateId));
			
			System.out.println(estateDTO.getAddress1());

			eServ.updateById(estateDTO, optionDTOList, estateImages);

			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{estateId}")
	public ResponseEntity<Void> delete(@PathVariable Long estateId) throws Exception {
		eServ.deleteById(estateId);

		return ResponseEntity.ok().build();
	}

}