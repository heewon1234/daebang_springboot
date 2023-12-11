package com.kdt.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entities.Board;
import com.kdt.domain.entities.Files;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.BoardUploadDTO;
import com.kdt.mappers.BoardMapper;
import com.kdt.repositories.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardMapper bMapper;

	@Autowired
	private BoardRepository bRepo;

	public void insertBoardContents(BoardUploadDTO dto, String[] delImgList) throws Exception{

		// Board 테이블에 내용 추가
		dto.setViewCount(0L);
		Board board = bMapper.toEntity(dto);
		board.setFiles(new HashSet<>());
		board.setWriteDate(new Timestamp(System.currentTimeMillis()));
		Long parentSeq = bRepo.save(board).getSeq();

		// Files 테이블에 내용 추가 ( input )
		Set<Files> entityFiles = board.getFiles();
		List<MultipartFile> multiList = dto.getFiles();

		if(multiList != null && multiList.size() != 0) {
			String filePath = "C:/uploads";
			File uploadFilePath = new File(filePath);
			if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}

			String realPath = "C:/uploads/board";
			File uploadPath = new File(realPath);
			if(!uploadPath.exists()) {uploadPath.mkdir();}

			for(MultipartFile file : multiList) {
				if(file != null) {
					String oriName = file.getOriginalFilename();
					String sysName = UUID.randomUUID()+"_"+oriName;
					file.transferTo(new File(uploadPath,sysName));
					entityFiles.add(new Files(null,oriName,sysName,parentSeq,"input"));
				}	
			}
		}
		
		bRepo.save(board);
		
		// 서버에 있는 쓸모없는 이미지 파일 삭제
		delServerFile(delImgList);
	}

	public List<BoardDTO> selectAllFreeBoardContents(){
		return bMapper.toDtoList(bRepo.findAllByBoardTitle("자유게시판"));
	}
	public List<BoardDTO> selectAllRoomBoardContents(){
		return bMapper.toDtoList(bRepo.findAllByBoardTitle("양도게시판"));
	}	

	public BoardDTO boardContents(Long seq) {
		return bMapper.toDto(bRepo.findById(seq).get());
	}
	
	public void delServerFile(String[] delFileList) throws Exception{
		String filePath = "C:/uploads";
		File uploadFilePath = new File(filePath);
		if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}

		String realPath = "C:/uploads/board";
		File uploadPath = new File(realPath);
		if(!uploadPath.exists()) {uploadPath.mkdir();}
		
		for(String delFile : delFileList) {
			Path path = Paths.get(uploadPath + "/" + delFile);
			java.nio.file.Files.deleteIfExists(path);
		}
	}

}
