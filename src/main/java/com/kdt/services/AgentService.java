package com.kdt.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.controllers.BoardController;
import com.kdt.domain.entities.AgentProfile;
import com.kdt.domain.entities.EstateImage;
import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.dto.AgentProfileDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.UpdateEstateDTO;
import com.kdt.mappers.AgentMapper;
import com.kdt.mappers.AgentProfileMapper;
import com.kdt.mappers.NewEstateMapper;
import com.kdt.repositories.AgentProfileRepository;
import com.kdt.repositories.AgentRepository;
import com.kdt.repositories.NewEstateRepository;

@Service
public class AgentService {
	@Autowired
	private AgentRepository aRepo;
	@Autowired
	private AgentProfileRepository apRepo;

	@Autowired
	private AgentMapper aMapper;
	@Autowired
	private AgentProfileMapper apMapper;

	@Autowired
	private NewEstateRepository nRepo;

	@Autowired
	private NewEstateMapper nMapper;

	private final PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(AgentService.class);

	// @Autowired
	public AgentService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public List<RealEstateAgentDTO> getAll() {
		List<RealEstateAgent> list = aRepo.findAll();
		List<RealEstateAgentDTO> dtos = aMapper.toDtoList(list);
		return dtos;
	}

	public void deleteById(String email) {
		RealEstateAgent e = aRepo.findById(email).get();
		aRepo.delete(e);
	}
	public String getNamebyId(String estateid) {
		String name = aRepo.findNamebyId(estateid);
		return name;
	}
	public void approve(String email) {
		RealEstateAgent e = aRepo.findById(email).get();
		e.setEnabled(true);
		aRepo.save(e);
	}

	public void revoke_approval(String email) {
		RealEstateAgent e = aRepo.findById(email).get();
		e.setEnabled(false);
		aRepo.save(e);
	}

	public void signup(RealEstateAgentDTO RealEstateAgentDTO) {
		String crypPw = passwordEncoder.encode(RealEstateAgentDTO.getPw());
		RealEstateAgentDTO.setPw(crypPw);
		RealEstateAgentDTO.setManners_temperature(36.5);
		RealEstateAgent e = aMapper.toEntity(RealEstateAgentDTO);
		aRepo.save(e);
	}

	public boolean isEstateNumber(String number) {
		RealEstateAgent e = aRepo.findByEstateNumber(number);
		return e != null; // 해당 estateNumber가 존재하면 true, 존재하지 않으면 false 반환
	}

	// 마이페이지 정보 띄우기
	public RealEstateAgentDTO estateInfo(String id) {
		RealEstateAgent a = aRepo.findById(id).get();
		RealEstateAgentDTO adto = aMapper.toDto(a);
		return adto;
	}

	// 공인중개사 비밀번호 변경
	@Transactional
	public void changePw(String email, String pw) {
		String hashedPassword = passwordEncoder.encode(pw);
		aRepo.changePw(email, hashedPassword);
	}

	// 공인중개사 정보 변경
	public void updateMyInfo(UpdateEstateDTO dto) {
		RealEstateAgent a = aRepo.findById(dto.getId()).get();
		RealEstateAgentDTO adto = new RealEstateAgentDTO(a.getEmail(), a.getPw(), a.getEstateName(),
				a.getEstateNumber(), dto.getName(), dto.getAddress(), dto.getPhone(), a.getManners_temperature(),
				dto.getLatitude(), dto.getLongitude(), a.getRole(), a.isEnabled(),dto.getContent());
		aMapper.updateEntityFromDTO(adto, a);
		aRepo.save(a);
	}

	public List<RealEstateAgentDTO> getId(String name, String phone) {
		List<RealEstateAgent> list = aRepo.selectbynamephone(name, phone);
		List<RealEstateAgentDTO> dtos = aMapper.toDtoList(list);
		return dtos;
	}

	public List<RealEstateAgentDTO> getDto(String name, String phone, String email) {
		List<RealEstateAgent> list = aRepo.findByNameAndPhoneAndEmail(name, phone, email);
		List<RealEstateAgentDTO> dtos = aMapper.toDtoList(list);
		return dtos;
	}

	@Transactional
	public void insertImage(String loginId, List<MultipartFile> images) {

		try {
			// 사진 파일 삭제 ->
			// 실제로 지울 파일 이름 검색
			List<AgentProfile> apList = apRepo.findAllByParentEmail(loginId);
			List<String> delFileList = new ArrayList<>();
			for (AgentProfile image : apList) {
				delFileList.add(image.getSysName());
			}
			delServerFile(delFileList);
			// DB에서 삭제
			apRepo.deleteByParentEmail(loginId);
			// <- 사진 파일 삭제

			// 사진 파일 입력 ->
			if (images.size() != 0) {
				String upload = "/uploads/agentProfile/";
				File uploadPath = new File(upload);
				if (!uploadPath.exists()) {
					uploadPath.mkdir();
				}

				for (MultipartFile image : images) {
					String oriName = image.getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;

					image.transferTo(new File(uploadPath, sysName));
					apRepo.save(new AgentProfile(null, oriName, sysName, loginId));
				}
			}
			// <- 사진 파일 입력
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 사진 파일 삭제
	public void delServerFile(List<String> delFileList) throws Exception {
		String filePath = "/uploads";
		File uploadFilePath = new File(filePath);
		if (!uploadFilePath.exists()) {
			uploadFilePath.mkdir();
		}

		String realPath = "/uploads/agentProfile/";
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
	
	// 대표 이미지 한 장이긴 한데 만약의 경우를 대비해 리스트로 로드
	public List<AgentProfileDTO> getImageById(String loginId) {
		List<AgentProfile> ap = apRepo.findAllByParentEmail(loginId);
		
		List<AgentProfileDTO> apList = apMapper.toDtoList(ap);
		
		return apList;
	}
}