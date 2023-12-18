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

import com.kdt.domain.entities.Review;
import com.kdt.domain.entities.ReviewFiles;
import com.kdt.dto.ReviewDTO;
import com.kdt.dto.UploadReviewDTO;
import com.kdt.mappers.ReviewMapper;
import com.kdt.repositories.EstateRepository;
import com.kdt.repositories.ReviewFilesRepository;
import com.kdt.repositories.ReviewRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	private ReviewMapper rMapper;
	
	@Autowired
	private EstateRepository eRepo;
	
	@Autowired
	private ReviewRepository rRepo;
	
	@Autowired
	private ReviewFilesRepository rfRepo;
	
	// 리뷰 등록
	@Transactional
	public void insertReview(UploadReviewDTO dto) throws Exception{
		// Review 테이블에 내용 추가
		String realEstateNumber = eRepo.findById(dto.getEstateId()).get().getWriter();
		dto.setRealEstateNumber(realEstateNumber);
		Review review = rMapper.toEntity(dto);
		review.setFiles(new HashSet<>());
		review.setWriteDate(new Timestamp(System.currentTimeMillis()));
		Long parentSeq = rRepo.save(review).getSeq();
		// ReviewFiles 테이블에 내용 추가
		Set<ReviewFiles> entityFiles = review.getFiles();
		
		List<MultipartFile> multiList = dto.getFiles();

		if(multiList != null && multiList.size() != 0) {
			
			String filePath = "C:/uploads";
			File uploadFilePath = new File(filePath);
			if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}

			String realPath = "C:/uploads/review";
			File uploadPath = new File(realPath);
			if(!uploadPath.exists()) {uploadPath.mkdir();}

			for(MultipartFile file : multiList) {
				if(file != null) {
					String oriName = file.getOriginalFilename();
					String sysName = UUID.randomUUID()+"_"+oriName;
					file.transferTo(new File(uploadPath,sysName));
					entityFiles.add(new ReviewFiles(null,sysName,oriName,parentSeq));
				}	
			}
		}
		rRepo.save(review);
	}
	
	// 리뷰 목록 불러오기
	public List<ReviewDTO> selectReviewByEstateId(Long estateId){
		return rMapper.toDtoList(rRepo.findByEstateId(estateId));
	}
	
	// 리뷰 삭제
	@Transactional
	public void delReviewBySeq(Long seq) {
		Review review = rRepo.findById(seq).get();
		rRepo.delete(review);
	}
	
	// 리뷰 수정 - 내용 가져오기
	public ReviewDTO selectReviewBySeq(Long seq) {
		return rMapper.toDto(rRepo.findById(seq).get());	
	}
	
	// 리뷰 수정
	@Transactional
	public void updateReview(UploadReviewDTO dto, String[] delFileList) throws Exception{
		delDBFile(delFileList);	
		Review review = rRepo.findBySeqAndId(dto.getSeq(), dto.getId()).get();
		
		Set<ReviewFiles> entityFiles = review.getFiles();
		List<MultipartFile> multiList = new ArrayList<>();
		if(dto.getFiles()!=null) {
			for(MultipartFile file : dto.getFiles()) {
				if(file != null) {
					multiList.add(file);
				}
			}
		}
		dto.setFiles(null);
		rMapper.updateEntityFromDTO(dto, review);
		rRepo.save(review);
		
		if(multiList != null && multiList.size() != 0) {
			String filePath = "C:/uploads";
			File uploadFilePath = new File(filePath);
			if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}

			String realPath = "C:/uploads/review";
			File uploadPath = new File(realPath);
			if(!uploadPath.exists()) {uploadPath.mkdir();}

			for(MultipartFile file : multiList) {
				if(file != null) {
					String oriName = file.getOriginalFilename();
					String sysName = UUID.randomUUID()+"_"+oriName;
					file.transferTo(new File(uploadPath,sysName));
					entityFiles.add(new ReviewFiles(null,sysName,oriName,dto.getSeq()));
				}	
			}
		}
		rRepo.save(review);
		entityManager.flush();
		delServerFile(delFileList);	
	}
	
	// DB 파일 삭제
	private void delDBFile(String[] delFileList) {
		if(delFileList!=null) {
			for(String delFile : delFileList) {
				if(delFile!=null) {
					ReviewFiles files = rfRepo.findBySysName(delFile);
					if (files != null ) {
						entityManager.remove(files);
						entityManager.flush();
					}
					
				}
			}
		}
	}
	
	// 서버 파일 삭제
	private void delServerFile(String[] delFileList) throws Exception{
		String filePath = "C:/uploads";
		File uploadFilePath = new File(filePath);
		if(!uploadFilePath.exists()) {uploadFilePath.mkdir();}

		String realPath = "C:/uploads/review";
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
