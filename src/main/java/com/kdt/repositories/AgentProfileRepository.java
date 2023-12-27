package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.AgentProfile;
import com.kdt.domain.entities.EstateImage;

public interface AgentProfileRepository extends JpaRepository<AgentProfile, Long>{
	Long deleteByParentEmail(String parentEmail);
	
	// 지울 파일 이름 검색
	List<AgentProfile> findAllByParentEmail(String parentEmail);
}
