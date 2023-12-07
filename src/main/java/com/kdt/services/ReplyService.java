package com.kdt.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.Reply;
import com.kdt.dto.ReplyDTO;
import com.kdt.mappers.ReplyMapper;
import com.kdt.repositories.ReplyRepository;

import jakarta.transaction.Transactional;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository rRepo;
	
	@Autowired
	private ReplyMapper rMapper;
	
	public List<ReplyDTO> insertReply(ReplyDTO dto) {
		Reply reply = rMapper.toEntity(dto);
		reply.setWriteDate(new Timestamp(System.currentTimeMillis()));
		rRepo.save(reply);
		return rMapper.toDtoList(rRepo.findAllByParentSeq(dto.getParentSeq()));
	}
	
	@Transactional
	public List<ReplyDTO> updateReply(ReplyDTO dto) {
		Reply reply = rRepo.findById(dto.getSeq()).get();
		rMapper.updateEntityFromDTO(dto,reply);
		rRepo.save(reply);
		return rMapper.toDtoList(rRepo.findAllByParentSeq(reply.getParentSeq()));
	}
	
}
