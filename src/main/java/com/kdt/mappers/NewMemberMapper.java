package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.NewMember;
import com.kdt.dto.NewMemberDTO;

@Mapper(componentModel = "spring")
public interface NewMemberMapper extends GenericMapper<NewMemberDTO, NewMember>{

}
