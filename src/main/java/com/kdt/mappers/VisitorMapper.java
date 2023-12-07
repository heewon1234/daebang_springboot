package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Visitor;
import com.kdt.dto.VisitorDTO;

@Mapper(componentModel = "spring")
public interface VisitorMapper extends GenericMapper<VisitorDTO, Visitor>{

}
