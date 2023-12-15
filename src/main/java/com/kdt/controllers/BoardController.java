package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.BoardDTO;
import com.kdt.dto.BoardUploadDTO;
import com.kdt.security.SecurityUser;
import com.kdt.services.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	BoardService bServ;

	private SecurityUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null && auth.getPrincipal() instanceof SecurityUser) {
			SecurityUser su = (SecurityUser)auth.getPrincipal();
			return su;
		}
		return null;
	}

	// 게시글 삽입
	@PostMapping
	public ResponseEntity<Void> insertBoardContents(BoardUploadDTO dto, String[] delImgList) throws Exception{
		dto.setWriter(getUser().getUsername());
		bServ.insertBoardContents(dto, delImgList);
		return ResponseEntity.ok().build();
	}

	// 게시글 수정
	@PutMapping
	public ResponseEntity<Void> editBoardContents(BoardUploadDTO dto, String[] delImgList, String[] delFileList) throws Exception{
		System.out.println(delFileList.length+"길이");
		dto.setWriter(getUser().getUsername());
		bServ.editBoardContents(dto, delImgList, delFileList);
		return ResponseEntity.ok().build();
	}

	// 게시글 목록 불러오기
	@GetMapping("/freeBoardList")
	public ResponseEntity<List<BoardDTO>> selectAllFreeBoardContents(){
		List<BoardDTO> list = bServ.selectAllFreeBoardContents(getUser() != null ? getUser().getUsername() : null);
		return ResponseEntity.ok(list);

	}

	@GetMapping("/roomBoardList")
	public ResponseEntity<List<BoardDTO>> selectAllRoomBoardContents(){
		List<BoardDTO> list = bServ.selectAllRoomBoardContents(getUser() != null ? getUser().getUsername() : null);
		return ResponseEntity.ok(list);

	}
	
	@GetMapping("/favBoardList")
	public ResponseEntity<List<BoardDTO>> selectAllFavBoardContents(){
		List<BoardDTO> list = bServ.selectAllFavBoardContents(getUser() != null ? getUser().getUsername() : null);
		return ResponseEntity.ok(list);
	}

	// 게시글 내용 불러오기
	@GetMapping("/boardContents/{contentsSeq}")
	public ResponseEntity<BoardDTO> boardContents(@PathVariable Long contentsSeq){
		BoardDTO dto = bServ.boardContents(contentsSeq);
		return ResponseEntity.ok(dto);
	}

	// 게시글 삭제
	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> delBoardContents(@PathVariable Long seq, @RequestBody String[] imgList) throws Exception{
		bServ.delBoardContents(seq,imgList);
		return ResponseEntity.ok().build();
	}

}
