package com.kdt.services;


import java.io.File;
import java.sql.Timestamp;
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
	
	public void insertBoardContents(BoardUploadDTO dto) throws Exception{
		dto.setViewCount(0L);
		Board board = bMapper.toEntity(dto);
		board.setWriteDate(new Timestamp(System.currentTimeMillis()));
		
		Long parentSeq = bRepo.save(board).getSeq();
		Set<Files> entityFiles = board.getFiles();
		List<MultipartFile> multiList = dto.getFiles();
		
		String boardTitle = dto.getBoardTitle().equals("자유게시판") ? "freeBoard" : "roomBoard";
		
		if(multiList != null && multiList.size() != 0) {
			String upload = "e:/uploads/"+boardTitle;
			File uploadPath = new File(upload);
			if(!uploadPath.exists()) {uploadPath.mkdir();}
			
			for(MultipartFile file : multiList) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				
				file.transferTo(new File(uploadPath,sysName));
				
				entityFiles.add(new Files(null,oriName,sysName,parentSeq,"input"));
			}
		}
		
		bRepo.save(board);
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
}
