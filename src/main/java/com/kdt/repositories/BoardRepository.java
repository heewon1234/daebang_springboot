package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@EntityGraph(attributePaths = {"replies","files"})
	List<Board> findAllByBoardTitle(String boardTitle);
	
}
