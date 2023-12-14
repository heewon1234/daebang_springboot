package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long> {
	@EntityGraph(attributePaths = { "building", "heatingSystem", "room", "structure", "transaction", "images",
			"optionList" })
	List<Estate> findAll();
}
