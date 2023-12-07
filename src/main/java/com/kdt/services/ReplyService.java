package com.kdt.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Reply;
import com.kdt.dto.ReplyDTO;
import com.kdt.mappers.ReplyMapper;
import com.kdt.repositories.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository rRepo;
	
	@Autowired
	private ReplyMapper rMapper;
	
	public void insertReply(ReplyDTO dto) {
		Reply reply = rMapper.toEntity(dto);
		reply.setWriteDate(new Timestamp(System.currentTimeMillis()));
		rRepo.save(reply);
	}
	
}
