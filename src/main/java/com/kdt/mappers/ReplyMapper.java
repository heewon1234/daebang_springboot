package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Reply;
import com.kdt.dto.ReplyDTO;

@Mapper(componentModel = "spring")
public interface ReplyMapper extends GenericMapper<ReplyDTO, Reply>{

}
