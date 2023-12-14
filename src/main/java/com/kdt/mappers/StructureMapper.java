package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Structure;
import com.kdt.dto.StructureDTO;

@Mapper(componentModel = "spring")
public interface StructureMapper extends GenericMapper<StructureDTO,Structure>{

}
