package com.kdt.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Board;
import com.kdt.dto.BoardDTO;

public interface BoardRepository extends JpaRepository<Board, Long>{

	@EntityGraph(attributePaths = {"replies","files"})
	List<Board> findAllByBoardTitle(String boardTitle);

	@Query("SELECT new com.kdt.domain.entities.Board(b.seq, b.boardTitle, b.title, b.writer,b.writeDate, b.header, b.contents, b.viewCount,"
			+ "CASE WHEN (b.seq = fb.parentSeq) THEN 'true' ELSE 'false' END) FROM Board b "
			+ "LEFT JOIN FavoriteBoard fb ON b.seq = fb.parentSeq and fb.id = ?2 WHERE b.boardTitle = ?1 ORDER BY b.seq DESC")
	List<Board> selectBoardContentswithFav(String boardTitle, String id);
	
	@Query("SELECT new com.kdt.domain.entities.Board(b.seq, b.boardTitle, b.title, b.writer,b.writeDate, b.header, b.contents, b.viewCount,"
			+ "CASE WHEN (b.seq = fb.parentSeq) THEN 'true' ELSE 'false' END) FROM Board b "
			+ "INNER JOIN FavoriteBoard fb ON b.seq = fb.parentSeq and fb.id = ?1 ORDER BY b.seq DESC")
	List<Board> selectFavBoardContents(String id);
	
}
