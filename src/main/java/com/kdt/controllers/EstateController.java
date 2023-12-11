package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.BoardDTO;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.EstateOptionDTO;
import com.kdt.dto.EstateRequestDTO;
import com.kdt.services.EstateService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/estateManage/")
public class EstateController {

	@Autowired
	private HttpSession session;

	@Autowired
	private EstateService eServ;

	@PostMapping
	@RequestMapping("estateInsert1")
	public ResponseEntity<Void> insert1(@RequestBody EstateDTO dto) throws Exception {
		session.setAttribute("estateDTO", dto);

		return ResponseEntity.ok().build();
	}

	@PostMapping
	@RequestMapping("estateInsert2")
	public ResponseEntity<Void> insert2(@RequestBody EstateRequestDTO requestDTO) throws Exception {
		EstateDTO dto = requestDTO.getEstateDTO();
		String[] optionList = requestDTO.getOptionList();

		// 매물 ->
		EstateDTO estateDTO = (EstateDTO) session.getAttribute("estateDTO");

		estateDTO.setTransactionCode(dto.getTransactionCode());
		estateDTO.setDeposit(dto.getDeposit());
		estateDTO.setPrice(dto.getPrice());
		estateDTO.setMaintenanceCost(dto.getMaintenanceCost());
		estateDTO.setRoomFloors(dto.getRoomFloors());
		estateDTO.setBuildingFloors(dto.getBuildingFloors());
		// <- 매물

		// 옵션 ->
		List<EstateOptionDTO> optionDTOList = new ArrayList<>();
		for (String optionCode : optionList) {
			optionDTOList.add(new EstateOptionDTO(null, null, optionCode));
		}
		// <- 옵션

		session.setAttribute("estateDTO", estateDTO);
		session.setAttribute("optionDTOList", optionDTOList);

		return ResponseEntity.ok().build();
	}

	@PostMapping
	@RequestMapping("estateInsert3")
	public ResponseEntity<Void> insert3(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("title") String title, @RequestParam("contents") String contents,
			@RequestParam("memo") String memo) throws Exception {

		EstateDTO estateDTO = (EstateDTO) session.getAttribute("estateDTO");
		List<EstateOptionDTO> optionDTOList = (List<EstateOptionDTO>) session.getAttribute("optionDTOList");

		estateDTO.setTitle(title);
		estateDTO.setContents(contents);
		estateDTO.setMemo(memo);

		/*
		 * System.out.println(estateDTO.getRoomCode());
		 * System.out.println(estateDTO.getStructureCode());
		 * System.out.println(estateDTO.getBuildingCode());
		 * System.out.println(estateDTO.getHeatingCode());
		 * System.out.println(estateDTO.getDeposit());
		 * System.out.println(estateDTO.getPrice());
		 * System.out.println(estateDTO.getArea());
		 * System.out.println(estateDTO.getZipcode());
		 * System.out.println(estateDTO.getAddress());
		 * System.out.println(estateDTO.getRoomFloors());
		 * System.out.println(estateDTO.getBuildingFloors());
		 * System.out.println(estateDTO.getMaintenanceCost());
		 * System.out.println(estateDTO.getTitle());
		 * System.out.println(estateDTO.getContents());
		 * System.out.println(estateDTO.getMemo());
		 */

		eServ.insertEstate(estateDTO, optionDTOList, images);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<EstateDTO>> selectAll() {
		List<EstateDTO> list = eServ.selectAll();
		
		System.out.println(list);
		
		return ResponseEntity.ok(list);
	}
	
	// roomName 가져오기
//	@GetMapping("/getRoomType/{roomCode}")
//	public ResponseEntity<String> getRoomTypeByRoomCode(@PathVariable String roomCode) {
//		String roomType = eServ.
//		
//		return ResponseEntity.ok(null);
//		
//	}

}