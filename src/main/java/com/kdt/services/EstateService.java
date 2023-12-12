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

import jakarta.transaction.Transactional;

@Service
public class EstateService {

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
	public void insertEstate(UploadEstateDTO dto, List<EstateOptionDTO> optionDTOList, List<MultipartFile> images)
			throws Exception {
		// 매물 입력 ->
		UploadEstate estate = ueMapper.toEntity(dto);

		// 관리비 입력 안 했으면 0
		if (estate.getMaintenanceCost() == null) {
			estate.setMaintenanceCost(0L);
		}
		// 작성일 입력
		estate.setWriteDate(new Timestamp(System.currentTimeMillis()));
		
		Long parentSeq = ueRepo.save(estate).getEstateId();
		// <- 매물 입력

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

	public List<EstateDTO> selectAll() {
		List<Estate> eList = eRepo.findAll();
		List<EstateDTO> list = eMapper.toDtoList(eList);

		return list;
	}

	@Transactional
	public void deleteById(Long estateId) throws Exception {
		// 매물 옵션 정보 삭제
		eoRepo.deleteByEstateCode(estateId);
		
		// 사진 파일 삭제 ->
		// 실제로 지울 파일 이름 검색
		List<EstateImage> eiList = eiRepo.findSysNamesByParentId(estateId);
		List<String> delFileList = new ArrayList<>();
		for (EstateImage image : eiList) {
			delFileList.add(image.getSysName());
		}
		delServerFile(delFileList);
		// DB에서 삭제
		eiRepo.deleteByParentId(estateId);
		// <- 사진 파일 삭제

		// 매물 정보 삭제
		eRepo.deleteById(estateId);

		return;
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
}
