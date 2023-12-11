package com.kdt.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FilesController {

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFile(MultipartFile[] files) throws Exception{
	
		List<String> list = new ArrayList<>();
		String filePath = "C:/uploads";
		File uploadFilePath = new File(filePath);
		if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}
		
		String realPath = "C:/uploads/board";
		File uploadPath = new File(realPath);
		if(!uploadPath.exists()) {uploadPath.mkdir();}
		
		if(files != null) {
			for(MultipartFile file : files) {
				if(file!=null) {
					String ori_name = file.getOriginalFilename();
					String sys_name = UUID.randomUUID() + "_" + ori_name;
					file.transferTo(new File(uploadPath+"/"+sys_name));
					list.add("/uploads/board/"+sys_name);
				}
			}
		}
		
		return ResponseEntity.ok(list);
	}

}
