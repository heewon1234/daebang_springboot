package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.ReviewApproval;
import com.kdt.dto.ReviewApprovalDTO;

public interface ReviewApprovalRepository extends JpaRepository<ReviewApproval, Long>{

	List<ReviewApproval> findAllByUserId(String loginId);
	
	ReviewApproval findByApprovalCodeAndEstateCode(String approvalCode, Long estateCode);
}
