package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadReviewApproval;

public interface UploadReviewApprovalRepository extends JpaRepository<UploadReviewApproval, Long>{
	List<UploadReviewApproval> findAllByEstateCodeAndApprovalCodeIn(Long estateCode, List<String> approvalCodes);
	
	Long deleteByEstateCode(Long estateId);
	
	Long countByEstateCodeAndApprovalCodeIn(Long estateCode, List<String> approvalCodes);
}
