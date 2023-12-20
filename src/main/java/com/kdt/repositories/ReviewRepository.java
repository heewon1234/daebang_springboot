package com.kdt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	@EntityGraph(attributePaths = {"files"})
	List<Review> findByEstateId(Long estateId);
	
	@EntityGraph(attributePaths = {"files"})
	Optional<Review> findById(Long seq);
	
	@EntityGraph(attributePaths = {"files"})
	Optional<Review> findBySeqAndId(Long seq,String id);
	
	@Query("select avg(r.score) from Review r where r.realEstateNumber=?1")
	double findAverageScoreByRealEstateNumber(String realEstateNumber);
}
