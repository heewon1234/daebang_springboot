package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.NewEstate;
import com.kdt.dto.NewEstateDTO;

@Mapper(componentModel = "spring")
public interface NewEstateMapper extends GenericMapper<NewEstateDTO, NewEstate> {

}
