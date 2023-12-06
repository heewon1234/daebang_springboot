package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.EstateDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/estateManage/")
public class EstateController {
	
	@Autowired
	private HttpSession session;
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody EstateDTO dto) throws Exception{
		System.out.println(dto.getRoomCode());
		System.out.println(dto.getBuildingCode());
		System.out.println(dto.getHeatingCode());
		System.out.println(dto.getArea());
		System.out.println(dto.getZipcode());
		System.out.println(dto.getAddress());
		
		//session.setAttribute("EstateDTO", dto);
		
		return null;
	}
}
