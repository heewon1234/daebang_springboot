package com.kdt.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.EstateDTO;
import com.kdt.dto.EstateRequestDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/estateManage/")
public class EstateController {

	@Autowired
	private HttpSession session;

	@PostMapping
	@RequestMapping("estateInsert1")
	public ResponseEntity<Void> insert1(@RequestBody EstateDTO dto) throws Exception{
		session.setAttribute("EstateDTO", dto);

		return null;
	}

	@PostMapping
	@RequestMapping("estateInsert2")
	public ResponseEntity<Void> insert2(@RequestBody EstateRequestDTO requestDTO) throws Exception{
		
		EstateDTO dto = requestDTO.getEstateDTO();
		String[] optionCodeList = requestDTO.getOptionCodeList();
		
		EstateDTO estateDTO = (EstateDTO)session.getAttribute("EstateDTO");
		
		estateDTO.setTransactionCode(dto.getTransactionCode());
		estateDTO.setDeposit(dto.getDeposit());
		estateDTO.setPrice(dto.getPrice());
		estateDTO.setMaintenanceCost(dto.getMaintenanceCost());
		estateDTO.setRoomFloors(dto.getRoomFloors());
		estateDTO.setBuildingFloors(dto.getBuildingFloors());

		session.setAttribute("EstateDTO", estateDTO);

		return null;
	}
	
	@PostMapping
	@RequestMapping("estateInsert3")
	public ResponseEntity<Void> insert3(@RequestBody EstateDTO dto) throws Exception{
		
		EstateDTO estateDTO = (EstateDTO)session.getAttribute("EstateDTO");
		
		estateDTO.setTitle(dto.getTitle());
		estateDTO.setContents(dto.getContents());
		estateDTO.setMemo(dto.getMemo());
		
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

		return null;
	}
}
