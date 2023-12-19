package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Building;
import com.kdt.dto.BuildingDTO;

@Mapper(componentModel = "spring")
public interface BuildingMapper extends GenericMapper<BuildingDTO,Building>{

}
