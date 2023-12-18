package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.SawEstate;
import com.kdt.dto.SawEstateDTO;

@Mapper(componentModel = "spring")
public interface SawEstateMapper extends GenericMapper<SawEstateDTO, SawEstate> {

}
