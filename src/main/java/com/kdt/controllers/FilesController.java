package com.kdt.controllers;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FilesController {

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFile(MultipartFile[] files, String path) throws Exception{
		System.out.println("dd");
		List<String> list = new ArrayList<>();
		String filePath = "C:/uploads";
		File uploadFilePath = new File(filePath);
		if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}
		
		String realPath="";
		System.out.println(path==null ? "null" : "notnull");
		if(path==null) {
			realPath = "C:/uploads/board";
		} else {
			realPath = "C:/uploads/temp_review";
		}

		File uploadPath = new File(realPath);
		if(!uploadPath.exists()) {uploadPath.mkdir();}

		if(files != null) {
			for(MultipartFile file : files) {
				if(file!=null) {
					String ori_name = file.getOriginalFilename();
					String sys_name = UUID.randomUUID() + "_" + ori_name;
					file.transferTo(new File(uploadPath+"/"+sys_name));
					if(path != null && path.equals("review")) {
						list.add("/uploads/temp_review/"+sys_name);
					} else {
						list.add("/uploads/board/"+sys_name);
					}
				}
			}
		}

		return ResponseEntity.ok(list);
	}
	
	@GetMapping
	public ResponseEntity<Object> downloadFile(@RequestParam String sysName,@RequestParam String oriName) throws Exception{
		System.out.println(sysName + "d");
		String realPath =  "C:/uploads/board/"+sysName;
		
		try {
			Path filePath = Paths.get(realPath);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			File file = new File(realPath);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(URLEncoder.encode(file.getName(), StandardCharsets.UTF_8)).build());
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}

}
