package com.kdt.services;

import java.io.File;
import java.sql.Timestamp;
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
import com.kdt.repositories.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper rMapper;
	
	@Autowired
	private EstateRepository eRepo;
	
	@Autowired
	private ReviewRepository rRepo;
	
	@Transactional
	public void insertReview(UploadReviewDTO dto) throws Exception{
		System.out.println("1");
		// Review 테이블에 내용 추가
		String realEstateNumber = eRepo.findById(dto.getEstateId()).get().getWriter();
		dto.setRealEstateNumber(realEstateNumber);
		Review review = rMapper.toEntity(dto);
		review.setFiles(new HashSet<>());
		review.setWriteDate(new Timestamp(System.currentTimeMillis()));
		Long parentSeq = rRepo.save(review).getSeq();
		System.out.println("2");
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
	
	public List<ReviewDTO> selectReviewByEstateId(Long estateId){
		return rMapper.toDtoList(rRepo.findByEstateId(estateId));
	}
	
	@Transactional
	public void delReviewBySeq(Long seq) {
		Review review = rRepo.findById(seq).get();
		rRepo.delete(review);
	}
	
}
