package com.kdt.controllers;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
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

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@RestController
@RequestMapping("/api/file")
public class FilesController {

	private final Storage storage = StorageOptions.getDefaultInstance().getService();
	private final String bucketName = "daebbang";

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFile(MultipartFile[] files, String path) throws Exception{

		List<String> list = new ArrayList<>();

		String realPath = path==null ? "board/" : "temp_review/";

		if(files != null) {
			for(MultipartFile file : files) {
				if(file!=null) {
					String ori_name = file.getOriginalFilename();
					String sys_name = UUID.randomUUID() + "_" + ori_name;

					BlobId blobId = BlobId.of(bucketName, realPath+sys_name);
					BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
					Blob blob = storage.create(blobInfo, file.getBytes());
					list.add("https://storage.googleapis.com/daebbang/"+realPath+sys_name);

				}
			}
		}

		return ResponseEntity.ok(list);
	}

	@GetMapping
	public ResponseEntity<Object> downloadFile(@RequestParam String sysName,@RequestParam String oriName) throws Exception{
		
		BlobId blobId = BlobId.of(bucketName, "board/"+sysName);
		Blob blob = storage.get(blobId);

		if (blob == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		InputStream inputStream = Channels.newInputStream(blob.reader());
		InputStreamResource resource = new InputStreamResource(inputStream);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(URLEncoder.encode(oriName, StandardCharsets.UTF_8)).build());

		return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);

	}

}
