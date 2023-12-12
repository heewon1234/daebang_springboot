package com.kdt.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

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
	
	@GetMapping
	public ResponseEntity<Void> downloadFile(@RequestParam String sysName,@RequestParam String oriName, HttpServletResponse response) throws Exception{
		String realPath =  "C:/uploads/board";
		File targetFile = new File(realPath + "/" + sysName);
		oriName = new String(oriName.getBytes("utf8"),"ISO-8859-1");
		response.setHeader("content-disposition", "attachment;filename="+oriName);

		try(DataInputStream dis = new DataInputStream(new FileInputStream(targetFile));
			DataOutputStream dos = new DataOutputStream(response.getOutputStream());){
			byte[] fileContents = dis.readAllBytes();
			dos.write(fileContents);
			dos.flush();
		}	
		return ResponseEntity.ok().build();
	}

}
