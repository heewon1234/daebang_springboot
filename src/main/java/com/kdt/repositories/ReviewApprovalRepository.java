package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.ReviewApproval;

public interface ReviewApprovalRepository extends JpaRepository<ReviewApproval, Long>{

	List<ReviewApproval> findAllByUserId(String loginId);
	//관리자
	List<ReviewApproval> findAllByApprovalCodeIn(List<String> approvalCodes);
	
//	ReviewApproval findByApprovalCodeAndEstateCode(String approvalCode, Long estateCode);
	ReviewApproval findByApprovalCodeAndEstateEstateId(String approvalCode, Long estateId);
	
	@Modifying
	@Query("UPDATE ReviewApproval ra SET ra.approvalCode = :approvalCode WHERE ra.seq = :seq")
	void updateStatue(Long seq, String approvalCode);
	
}
