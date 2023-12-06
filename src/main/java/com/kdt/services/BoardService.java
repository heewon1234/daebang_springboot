package com.kdt.services;

import java.sql.Timestamp;

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
		bRepo.save(board);
	}
	
}
