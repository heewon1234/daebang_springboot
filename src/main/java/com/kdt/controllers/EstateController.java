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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.EstateOptionDTO;
import com.kdt.dto.EstateRequestDTO;
import com.kdt.dto.UploadEstateDTO;
import com.kdt.services.EstateService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/estateManage/")
public class EstateController {

	@Autowired
	private HttpSession session;

	@Autowired
	private EstateService eServ;

//	@PostMapping
//	@RequestMapping("estateInsert1")
//	public ResponseEntity<Void> insert1(@RequestBody UploadEstateDTO dto) throws Exception {
//		// 세션 초기화
//		session.removeAttribute("estateDTO");
//		session.removeAttribute("optionDTOList");
//
//		dto.setWriter("test1234");
//		session.setAttribute("estateDTO", dto);
//
//		return ResponseEntity.ok().build();
//	}
//
//	@PostMapping
//	@RequestMapping("estateInsert2")
//	public ResponseEntity<Void> insert2(@RequestBody EstateRequestDTO requestDTO) throws Exception {
//		UploadEstateDTO dto = requestDTO.getEstateDTO();
//		String[] optionList = requestDTO.getOptionList();
//
//		// 매물 ->
//		UploadEstateDTO estateDTO = (UploadEstateDTO) session.getAttribute("estateDTO");
//
//		estateDTO.setTransactionCode(dto.getTransactionCode());
//		estateDTO.setDeposit(dto.getDeposit());
//		estateDTO.setPrice(dto.getPrice());
//		estateDTO.setMaintenanceCost(dto.getMaintenanceCost());
//		estateDTO.setRoomFloors(dto.getRoomFloors());
//		estateDTO.setBuildingFloors(dto.getBuildingFloors());
//		// <- 매물
//
//		// 옵션 ->
//		List<EstateOptionDTO> optionDTOList = new ArrayList<>();
//		for (String optionCode : optionList) {
//			optionDTOList.add(new EstateOptionDTO(null, null, optionCode));
//		}
//		// <- 옵션
//
//		session.setAttribute("estateDTO", estateDTO);
//		session.setAttribute("optionDTOList", optionDTOList);
//
//		return ResponseEntity.ok().build();
//	}
//
//	@PostMapping
//	@RequestMapping("estateInsert3")
//	public ResponseEntity<Void> insert3(@RequestParam("images") List<MultipartFile> images,
//			@RequestParam("title") String title, @RequestParam("contents") String contents,
//			@RequestParam("memo") String memo) throws Exception {
//
//		UploadEstateDTO estateDTO = (UploadEstateDTO) session.getAttribute("estateDTO");
//		List<EstateOptionDTO> optionDTOList = (List<EstateOptionDTO>) session.getAttribute("optionDTOList");
//
//		estateDTO.setTitle(title);
//		estateDTO.setContents(contents);
//		estateDTO.setMemo(memo);
//
//		eServ.insertEstate(estateDTO, optionDTOList, images);
//
//		return ResponseEntity.ok().build();
//	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestParam("realEstate") String realEstateJson,
			@RequestParam("optionList") String optionListJson,
			@RequestParam("estateImages") List<MultipartFile> estateImages) {
		
		List<EstateOptionDTO> optionDTOList = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// realEstateJson 문자열을 UploadEstateDTO 객체로 변환
			UploadEstateDTO estateDTO = objectMapper.readValue(realEstateJson, UploadEstateDTO.class);
			//임시 아이디(나중에 삭제해야 하는 코드)
			estateDTO.setWriter("test1234");
			
			// optionListJson를 파싱하여 EstateOptionDTO 객체 리스트로 변환
	        String[] optionArray = objectMapper.readValue(optionListJson, String[].class);
	        for (String optionCode : optionArray) {
	            optionDTOList.add(new EstateOptionDTO(null, null, optionCode));
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

	@DeleteMapping("/{estateId}")
	public ResponseEntity<Void> delete(@PathVariable Long estateId) throws Exception {
		eServ.deleteById(estateId);

		return ResponseEntity.ok().build();
	}

}