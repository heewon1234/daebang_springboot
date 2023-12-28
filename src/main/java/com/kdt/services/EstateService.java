package com.kdt.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.EstateImage;
import com.kdt.domain.entities.RealEstateViews;
import com.kdt.domain.entities.UploadEstate;
import com.kdt.domain.entities.UploadEstateOption;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.UploadEstateDTO;
import com.kdt.dto.UploadEstateOptionDTO;
import com.kdt.mappers.EstateImageMapper;
import com.kdt.mappers.EstateMapper;
import com.kdt.mappers.UploadEstateMapper;
import com.kdt.mappers.UploadEstateOptionMapper;
import com.kdt.repositories.EstateImageRepository;
import com.kdt.repositories.EstateRepository;
import com.kdt.repositories.RealEstateViewsRepository;
import com.kdt.repositories.UploadEstateOptionRepository;
import com.kdt.repositories.UploadEstateRepository;

import jakarta.transaction.Transactional;

@Service
public class EstateService {
	private static final Logger logger = LoggerFactory.getLogger(EstateService.class);

	@Autowired
	private UploadEstateMapper ueMapper;
	@Autowired
	private UploadEstateOptionMapper ueoMapper;
	@Autowired
	private EstateMapper eMapper;
	@Autowired
	private EstateImageMapper eiMapper;

	@Autowired
	private UploadEstateRepository ueRepo;
	@Autowired
	private UploadEstateOptionRepository ueoRepo;
	@Autowired
	private EstateImageRepository eiRepo;
	@Autowired
	private EstateRepository eRepo;
	@Autowired
	private RealEstateViewsRepository rRepo;
	
	//관리자 영역
	//매물 조회수 등록
	
	public void increaseViewCount(Long estateId) {
	    RealEstateViews views = rRepo.findByEstate_EstateId(estateId);
	    logger.debug("views : "+ views);

	    if (views != null) {
	        views.setViewCount(views.getViewCount() + 1);
	    } else {
	        views = new RealEstateViews();
	        views.setViewCount(1);
	        views.setEstateId(estateId); // 어떤 estate에 대한 조회수인지 설정해야 합니다.
	    }
	    rRepo.save(views);
	}


	//통계
	public List<Object[]> countByRoomCode() {
		return eRepo.countByRoom();
	}
	public Long countEstate() {
		return eRepo.countByEstate();
	}
	public Long countTodayByEstate() {
		return eRepo.countTodayByEstate();
	}
	
	@Transactional
	public void insertEstate(UploadEstateDTO dto, List<UploadEstateOptionDTO> optionDTOList,
			List<MultipartFile> images) {
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

			// 매물 옵션 입력 ->
			for (UploadEstateOptionDTO optionDTO : optionDTOList) {
				UploadEstateOption option = ueoMapper.toEntity(optionDTO);
				option.setEstateCode(parentSeq);

				ueoRepo.save(option);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<EstateDTO> selectById(String loginId) {

		List<Estate> eList = eRepo.findAllByRealEstateAgentEmail(loginId);
		
		List<EstateDTO> list = eMapper.toDtoList(eList);
		
		for(EstateDTO dto : list) {
			dto.getRealEstateAgent().setPw(null);
		}

		return list;
	}

	public List<EstateDTO> selectAll() {
		List<Estate> eList = eRepo.findAllBySoldStatusFalse();
		List<EstateDTO> list = eMapper.toDtoList(eList);

		for(EstateDTO dto : list) {
			dto.getRealEstateAgent().setPw(null);
		}
		
		return list;
	}
	public List<EstateDTO> selectLimitAll() {
		List<Estate> eList = eRepo.findTop6ByOrderByEstateIdDesc();
		logger.debug(eList.get(3).getAddress1());
		List<EstateDTO> list = eMapper.toDtoList(eList);
		
		for(EstateDTO dto : list) {
			dto.getRealEstateAgent().setPw(null);
		}
		
		return list;
	}
	public List<EstateDTO> selectWatchAll(List<Long> recent) {
	    // ID 순서를 유지하기 위해 LinkedHashMap 사용
	    Map<Long, Estate> estateMap = new LinkedHashMap<>();
	    for (Long id : recent) {
	        Estate estate = eRepo.findById(id).orElse(null);
	        if (estate != null) {
	            estateMap.put(id, estate);
	        }
	    }
	    List<EstateDTO> list = eMapper.toDtoList(new ArrayList<>(estateMap.values()));
	    
	    for(EstateDTO dto : list) {
			dto.getRealEstateAgent().setPw(null);
		}
	    
	    return list;
	}
	public List<String> selectImageAll(List<Long> recent) {
	    List<String> resultList = new ArrayList<>();

	    for (Long parentId : recent) {
	        List<String> sysNameList = eiRepo.selectbyparentIdordertBySeq(parentId);
	        resultList.addAll(sysNameList);
	    }

	    return resultList;
	}

	public UploadEstateDTO selectById(Long estateId) {
		UploadEstate estate = ueRepo.findById(estateId).get();
		UploadEstateDTO dto = ueMapper.toDto(estate);

		return dto;
	}

	public EstateDTO getById(Long estateId) {
		Estate estate = eRepo.findById(estateId).get();
		EstateDTO dto = eMapper.toDto(estate);
		
		dto.getRealEstateAgent().setPw(null);

		return dto;
	}

	@Transactional
	public void deleteById(Long estateId) {

		try {
			// 매물 옵션 정보 삭제
			ueoRepo.deleteByEstateCode(estateId);

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

			// 매물 정보 삭제
			ueRepo.deleteById(estateId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void updateById(UploadEstateDTO dto, List<UploadEstateOptionDTO> optionDTOList, List<MultipartFile> images) {
		Long estateId = dto.getEstateId();

		UploadEstate estate = ueRepo.findById(estateId).get();

		// 보증금 입력 안 했으면 0
		if (dto.getDeposit() == null) {
			dto.setDeposit(0L);
		}

		// 관리비 입력 안 했으면 0
		if (dto.getMaintenanceCost() == null) {
			dto.setMaintenanceCost(0L);
		}

		// 이미지 파일 수정한 경우
		if (images != null) {
			try {
				// 매물 옵션 정보 삭제
				ueoRepo.deleteByEstateCode(estateId);

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

				// 매물 옵션 입력 ->
				for (UploadEstateOptionDTO optionDTO : optionDTOList) {
					UploadEstateOption option = ueoMapper.toEntity(optionDTO);
					option.setEstateCode(estateId);

					ueoRepo.save(option);
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
						eiRepo.save(new EstateImage(null, oriName, sysName, estateId));
					}
				}
				// <- 사진 파일 입력

				// 매물 정보 업데이트
				ueMapper.updateEntityFromDTO(dto, estate);
				ueRepo.save(estate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 이미지 파일 수정하지 않은 경우
		else {
			try {
				// 매물 옵션 정보 삭제
				ueoRepo.deleteByEstateCode(estateId);
				// 매물 옵션 입력 ->
				for (UploadEstateOptionDTO optionDTO : optionDTOList) {
					UploadEstateOption option = ueoMapper.toEntity(optionDTO);
					option.setEstateCode(estateId);

					ueoRepo.save(option);
				}
				// <- 매물 옵션 입력
				ueMapper.updateEntityFromDTO(dto, estate);
				ueRepo.save(estate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 마이페이지 매물 불러오기
	public EstateDTO selectEstate(Long id) {
		Estate e = eRepo.findById(id).get();
		EstateDTO edto = eMapper.toDto(e);
		
		edto.getRealEstateAgent().setPw(null);
		
		return edto;
	}
	
	public void updateStatus(Long estateId) {
		Estate estate = eRepo.findById(estateId).get();
		estate.setSoldStatus(!estate.isSoldStatus());

		logger.debug("estate: " + estate);
		
		eRepo.save(estate);
	}
}
