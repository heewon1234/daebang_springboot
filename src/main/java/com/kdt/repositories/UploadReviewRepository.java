package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.UploadReview;

public interface UploadReviewRepository extends JpaRepository<UploadReview, Long> {

}
