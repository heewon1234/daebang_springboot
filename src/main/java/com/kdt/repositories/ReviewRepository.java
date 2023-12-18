package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Reply;
import com.kdt.domain.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	@EntityGraph(attributePaths = {"files"})
	List<Review> findByEstateId(Long estateId);
	
}
