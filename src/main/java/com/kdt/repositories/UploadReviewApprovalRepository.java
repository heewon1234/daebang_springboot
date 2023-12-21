package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadReviewApproval;

public interface UploadReviewApprovalRepository extends JpaRepository<UploadReviewApproval, Long>{
	UploadReviewApproval findByEstateCodeAndApprovalCodeIn(Long estateCode, List<String> approvalCodes);
	
	Long countByEstateCodeAndApprovalCodeIn(Long estateCode, List<String> approvalCodes);
}
