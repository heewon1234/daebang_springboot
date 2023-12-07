package com.kdt.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Board;
import com.kdt.dto.BoardDTO;
import com.kdt.mappers.BoardMapper;
import com.kdt.repositories.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardMapper bMapper;
	
	@Autowired
	private BoardRepository bRepo;
	
	public void insertBoardContents(BoardDTO dto) {
		dto.setViewCount(0L);
		Board board = bMapper.toEntity(dto);
		board.setWriteDate(new Timestamp(System.currentTimeMillis()));
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
