package com.kdt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	@EntityGraph(attributePaths = {"replies","files"})
	List<Board> findAllByBoardTitle(String boardTitle);
	@EntityGraph(attributePaths = {"seq"})
	List<Board> findTop6ByBoardTitleOrderBySeqDesc(String boardTitle);

	@EntityGraph(attributePaths = {"replies","files"})
	Optional<Board> findById(Long id);
	
	@Query("SELECT new com.kdt.domain.entities.Board(b.seq, b.boardTitle, b.title, b.writer,b.writeDate, b.header, b.contents, b.viewCount,"
			+ "CASE WHEN (b.seq = fb.parentSeq) THEN 'true' ELSE 'false' END) FROM Board b "
			+ "LEFT JOIN FavoriteBoard fb ON b.seq = fb.parentSeq and fb.id = ?2 WHERE b.boardTitle = ?1 ORDER BY b.seq DESC")
	List<Board> selectBoardContentswithFav(String boardTitle, String id);
	@Query("SELECT new com.kdt.domain.entities.Board(b.seq, b.boardTitle, b.title, b.writer,b.writeDate, b.header, b.contents, b.viewCount,"
			+ "CASE WHEN (b.seq = fb.parentSeq) THEN 'true' ELSE 'false' END) FROM Board b "
			+ "LEFT JOIN FavoriteBoard fb ON b.seq = fb.parentSeq and fb.id = ?2 WHERE b.boardTitle = ?1 ORDER BY b.seq DESC LIMIT 6")
	List<Board> selectTop6BoardContentswithFav(String boardTitle, String id);
	
	@Query("SELECT new com.kdt.domain.entities.Board(b.seq, b.boardTitle, b.title, b.writer,b.writeDate, b.header, b.contents, b.viewCount,"
			+ "CASE WHEN (b.seq = fb.parentSeq) THEN 'true' ELSE 'false' END) FROM Board b "
			+ "INNER JOIN FavoriteBoard fb ON b.seq = fb.parentSeq and fb.id = ?1 ORDER BY b.seq DESC")
	List<Board> selectFavBoardContents(String id);
	
	@Modifying
	@Query("update Board b set b.viewCount=b.viewCount+1 where b.seq=?1")
	void increaseViewCount(Long seq);
}
