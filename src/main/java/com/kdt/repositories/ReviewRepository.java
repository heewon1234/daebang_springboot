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
	@Query("select r from Review r inner join Estate e on e.estateId=r.estateId where realEstateNumber=?1")
	List<Review> findByRealEstateNumber(String realEstateNumber);
	
	
	@EntityGraph(attributePaths = {"files"})
	Optional<Review> findById(Long seq);
	
	@EntityGraph(attributePaths = {"files"})
	Optional<Review> findBySeqAndId(Long seq,String id);
	
	@Query("SELECT "
	        + "(1.5 * SUM(CASE WHEN r.score = 5 THEN 1 ELSE 0 END)) + "
	        + "(0.8 * SUM(CASE WHEN r.score = 4 THEN 1 ELSE 0 END)) + "
	        + "SUM(CASE WHEN r.score = 3 THEN 1 ELSE 0 END) - "
	        + "(0.8 * SUM(CASE WHEN r.score = 2 THEN 1 ELSE 0 END)) - "
	        + "(1.5 * SUM(CASE WHEN r.score = 1 THEN 1 ELSE 0 END)) "
	        + "FROM Review r "
	        + "WHERE r.realEstateNumber = ?1")
	double findAverageScoreByRealEstateNumber(String realEstateNumber);

}
