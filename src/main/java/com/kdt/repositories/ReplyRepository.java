package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	List<Reply> findAllByParentSeq(Long parentSeq);
	void deleteByParentSeq(Long parentSeq);
}
