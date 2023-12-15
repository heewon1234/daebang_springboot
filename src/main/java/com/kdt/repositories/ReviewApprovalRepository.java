package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.ReviewApproval;
import com.kdt.dto.ReviewApprovalDTO;

public interface ReviewApprovalRepository extends JpaRepository<ReviewApproval, Long>{

	@Query("select new com.kdt.dto.ReviewApprovalDTO(r.seq, r.user_id, r.estate_code, r.approval_code, r.write_date) from ReviewApproval r where user_id=?1")
	List<ReviewApprovalDTO> findAllByUserId(String loginId);
}
