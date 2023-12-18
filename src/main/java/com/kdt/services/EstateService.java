package com.kdt.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.EstateImage;
import com.kdt.domain.entities.EstateOption;
import com.kdt.domain.entities.UploadEstate;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.EstateOptionDTO;
import com.kdt.dto.UploadEstateDTO;
import com.kdt.mappers.EstateMapper;
import com.kdt.mappers.EstateOptionMapper;
import com.kdt.mappers.UploadEstateMapper;
import com.kdt.repositories.EstateImageRepository;
import com.kdt.repositories.EstateOptionRepository;
import com.kdt.repositories.EstateRepository;
import com.kdt.repositories.UploadEstateRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class EstateService {
	@Autowired
	private HttpSession session;

	@Autowired
	private UploadEstateMapper ueMapper;
	@Autowired
	private EstateOptionMapper eoMapper;
	@Autowired
	private EstateMapper eMapper;

	@Autowired
	private UploadEstateRepository ueRepo;
	@Autowired
	private EstateOptionRepository eoRepo;
	@Autowired
	private EstateImageRepository eiRepo;
	@Autowired
	private EstateRepository eRepo;

	@Transactional
	public void insertEstate(UploadEstateDTO dto, List<EstateOptionDTO> optionDTOList, List<MultipartFile> images) {
		UploadEstate estate = ueMapper.toEntity(dto);

		// 보증금 입력 안 했으면 0
		if (estate.getDeposit() == null) {
			estate.setDeposit(0L);
		}

		// 관리비 입력 안 했으면 0
		if (estate.getMaintenanceCost() == null) {
			estate.setMaintenanceCost(0L);
		}
		// 작성일 입력
		estate.setWriteDate(new Timestamp(System.currentTimeMillis()));

		try {
			// 매물 입력
			Long parentSeq = ueRepo.save(estate).getEstateId();
			
			// 연관 테이블 추가
			insertRelated(parentSeq, optionDTOList, images);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 연관 테이블 추가
	public void insertRelated(Long parentSeq, List<EstateOptionDTO> optionDTOList, List<MultipartFile> images)
			throws Exception {
		// 매물 옵션 입력 ->
		for (EstateOptionDTO optionDTO : optionDTOList) {
			EstateOption option = eoMapper.toEntity(optionDTO);
			option.setEstateCode(parentSeq);

			eoRepo.save(option);
		}
		// <- 매물 옵션 입력

		// 사진 파일 입력 ->
		if (images.size() != 0) {
			String upload = "c:/uploads/estateImages/";
			File uploadPath = new File(upload);
			if (!uploadPath.exists()) {
				uploadPath.mkdir();
			}

			for (MultipartFile image : images) {
				String oriName = image.getOriginalFilename();
				String sysName = UUID.randomUUID() + "_" + oriName;

				image.transferTo(new File(uploadPath, sysName));
				eiRepo.save(new EstateImage(null, oriName, sysName, parentSeq));
			}
		}
		// <- 사진 파일 입력
	}

	public List<EstateDTO> selectById(String loginId) {
		
		List<Estate> eList = eRepo.findAllByWriter(loginId);
		List<EstateDTO> list = eMapper.toDtoList(eList);

		return list;
	}
	
	public List<EstateDTO> selectAll() {
		List<Estate> eList = eRepo.findAll();
		List<EstateDTO> list = eMapper.toDtoList(eList);

		return list;
	}
	
	public UploadEstateDTO selectById(Long estateId) {
		UploadEstate estate = ueRepo.findById(estateId).get();
		UploadEstateDTO dto = ueMapper.toDto(estate);
		
		return dto;
	}

	@Transactional
	public void deleteById(Long estateId) {

		try {
			// 연관 테이블 삭제
			deleteRelatedById(estateId);
			// 매물 정보 삭제
			ueRepo.deleteById(estateId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 연관 테이블 삭제
	@Transactional
	public void deleteRelatedById(Long estateId) throws Exception {
		// 매물 옵션 정보 삭제
		eoRepo.deleteByEstateCode(estateId);

		// 사진 파일 삭제 ->
		// 실제로 지울 파일 이름 검색
		List<EstateImage> eiList = eiRepo.findAllByParentId(estateId);
		List<String> delFileList = new ArrayList<>();
		for (EstateImage image : eiList) {
			delFileList.add(image.getSysName());
		}
		delServerFile(delFileList);
		// DB에서 삭제
		eiRepo.deleteByParentId(estateId);
		// <- 사진 파일 삭제
	}

	// 사진 파일 삭제
	public void delServerFile(List<String> delFileList) throws Exception {
		String filePath = "C:/uploads";
		File uploadFilePath = new File(filePath);
		if (!uploadFilePath.exists()) {
			uploadFilePath.mkdir();
		}

		String realPath = "C:/uploads/estateImages/";
		File uploadPath = new File(realPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}

		if (delFileList != null) {
			for (String delFile : delFileList) {
				if (delFile != null) {
					Path path = Paths.get(uploadPath + "/" + delFile);
					java.nio.file.Files.deleteIfExists(path);
				}
			}
		}
	}

	// 매물 정보 수정
	@Transactional
	public void updateById(UploadEstateDTO dto, List<EstateOptionDTO> optionDTOList, List<MultipartFile> images) {
		Long estateId = dto.getEstateId();

		UploadEstate estate = ueRepo.findById(estateId).get();

		try {
			// 연관 테이블 삭제
			deleteRelatedById(estateId);
			// 삭제 후 재생성
			insertRelated(estateId, optionDTOList, images);
			ueMapper.updateEntityFromDTO(dto, estate);
			ueRepo.save(estate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// 마이페이지 매물 불러오기
	public EstateDTO selectEstate(Long id) {
		Estate e = eRepo.findById(id).get();
		EstateDTO edto = eMapper.toDto(e);
		return edto;
	}
}
