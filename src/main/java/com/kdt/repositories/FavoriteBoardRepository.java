package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.FavoriteBoard;

public interface FavoriteBoardRepository extends JpaRepository<FavoriteBoard, Long>{

	FavoriteBoard findByIdAndParentSeq(String id, Long parentSeq);
	
}
