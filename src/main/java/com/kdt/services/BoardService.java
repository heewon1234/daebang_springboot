package com.kdt.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.kdt.repositories.FileRepository;
import com.kdt.repositories.ReplyRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class BoardService {

	@Autowired
	private BoardMapper bMapper;

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private FileRepository fRepo;

	@Autowired
	private ReplyRepository rRepo;

	@Autowired
    private EntityManager entityManager;
	
	// 게시글 등록
	@Transactional
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
					entityFiles.add(new Files(null,sysName,oriName,parentSeq));
				}	
			}
		}

		bRepo.save(board);

		// 서버에 있는 쓸모없는 이미지 파일 삭제
		delServerFile(delImgList);
	}

	@Transactional
	public void editBoardContents(BoardUploadDTO dto, String[] delImgList, String[] delFileList) throws Exception{

		delDBFile(delFileList);	
		Board board = bRepo.findById(dto.getSeq()).get();

		Set<Files> entityFiles = board.getFiles();
		List<MultipartFile> multiList = new ArrayList<>();
		if(dto.getFiles()!=null) {
			for(MultipartFile file : dto.getFiles()) {
				if(file != null) {
					multiList.add(file);
				}
			}
		}
		dto.setFiles(null);
		bMapper.updateEntityFromDTO(dto,board);
		bRepo.save(board);

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
					entityFiles.add(new Files(null,sysName,oriName,dto.getSeq()));
				}	
			}
		}
		bRepo.save(board);

		if(delImgList!=null && delImgList.length!=0) {
			delServerFile(delImgList);
		}
		if(delFileList!=null && delFileList.length!=0) {
			delServerFile(delFileList);
		}	
	}

	// 자유게시판 글 목록 불러오기
	public List<BoardDTO> selectAllFreeBoardContents(String id){
		if(id==null) {
			return bMapper.toDtoList(bRepo.findAllByBoardTitle("자유게시판"));
		} else {
			return bMapper.toDtoList(bRepo.selectBoardContentswithFav("자유게시판", id));
		}
	}

	// 양도게시판 글 목록 불러오기
	public List<BoardDTO> selectAllRoomBoardContents(String id){
		if(id==null) {
			return bMapper.toDtoList(bRepo.findAllByBoardTitle("양도게시판"));
		} else {
			return bMapper.toDtoList(bRepo.selectBoardContentswithFav("양도게시판", id));
		}
		
	}	
	
	// 즐겨찾기 게시판 글 목록 불러오기
	public List<BoardDTO> selectAllFavBoardContents(String id){
		if(id==null) {
			return new ArrayList<BoardDTO>();
		}
		return bMapper.toDtoList(bRepo.selectFavBoardContents(id));
	}

	// 게시글 내용 불러오기
	@Transactional
	public BoardDTO boardContents(Long seq) {
		System.out.println("d12213");
		bRepo.increaseViewCount(seq);
		return bMapper.toDto(bRepo.findById(seq).get());
	}

	// 게시글 삭제
	@Transactional
	public void delBoardContents(Long seq, String[] imgList) throws Exception{
		String[] delFileList = fRepo.findSysNameByParentSeq(seq);

		Board board = bRepo.findById(seq).get();
		bRepo.delete(board);

		delServerFile(delFileList); // 인풋 파일 삭제
		delServerFile(imgList); // 이미지태그 삭제

	}

	private void delDBFile(String[] delFileList) {
		if(delFileList!=null) {
			for(String delFile : delFileList) {
				if(delFile!=null) {
					Files files = fRepo.findBySysName(delFile);
					if (files != null ) {
						entityManager.remove(files);
						entityManager.flush();
					}
					
				}
			}
		}
	}
	
	// 서버 파일 삭제 함수
	private void delServerFile(String[] delFileList) throws Exception{
		String filePath = "C:/uploads";
		File uploadFilePath = new File(filePath);
		if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}

		String realPath = "C:/uploads/board";
		File uploadPath = new File(realPath);
		if(!uploadPath.exists()) {uploadPath.mkdir();}

		if(delFileList != null) {
			for(String delFile : delFileList) {
				if(delFile != null) {
					Path path = Paths.get(uploadPath + "/" + delFile);
					java.nio.file.Files.deleteIfExists(path);
				}
			}
		}
	}

}
