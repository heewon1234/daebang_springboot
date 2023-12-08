package com.kdt.services;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.EstateImage;
import com.kdt.domain.entities.EstateOption;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.EstateOptionDTO;
import com.kdt.mappers.EstateMapper;
import com.kdt.mappers.EstateOptionMapper;
import com.kdt.repositories.EstateImageRepository;
import com.kdt.repositories.EstateOptionRepository;
import com.kdt.repositories.EstateRepository;

import jakarta.transaction.Transactional;

@Service
public class EstateService {

	@Autowired
	private EstateMapper eMapper;
	@Autowired
	private EstateOptionMapper eoMapper;

	@Autowired
	private EstateRepository eRepo;
	@Autowired
	private EstateOptionRepository eoRepo;
	@Autowired
	private EstateImageRepository eiRepo;

	@Transactional
	public void insertEstate(EstateDTO dto, List<EstateOptionDTO> optionDTOList, List<MultipartFile> images) throws Exception {
		// 매물 입력 ->
		Estate estate = eMapper.toEntity(dto);

		if(estate.getMaintenanceCost() == null) {
			estate.setMaintenanceCost(0L);
		}
		estate.setWriteDate(new Timestamp(System.currentTimeMillis()));
		Long parentSeq = eRepo.save(estate).getEstateId();

		// <- 매물 입력

		// 매물 옵션 입력 ->
		for(EstateOptionDTO optionDTO : optionDTOList) {
			EstateOption option = eoMapper.toEntity(optionDTO);
			option.setEstateCode(parentSeq);

			eoRepo.save(option);
		}
		// <- 매물 옵션 입력

		// 사진 파일 입력 ->
		if(images.size() != 0) {
			String upload = "c:/uploads/estateImages/";
			File uploadPath = new File(upload);
			if(!uploadPath.exists()) {uploadPath.mkdir();}

			for(MultipartFile image :images) {
				String oriName = image.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;

				image.transferTo(new File(uploadPath, sysName));
				eiRepo.save(new EstateImage(null, oriName, sysName, parentSeq));
			}
		}
		// <- 사진 파일 입력
	}
}
