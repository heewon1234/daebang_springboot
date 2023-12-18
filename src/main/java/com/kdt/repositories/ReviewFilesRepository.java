package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.ReviewFiles;

public interface ReviewFilesRepository extends JpaRepository<ReviewFiles, Long>{

	ReviewFiles findBySysName(String sysName);

}
