package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.EstateOption;
import com.kdt.dto.EstateOptionDTO;

@Mapper(componentModel = "spring")
public interface EstateOptionMapper extends GenericMapper<EstateOptionDTO,EstateOption>{

}
