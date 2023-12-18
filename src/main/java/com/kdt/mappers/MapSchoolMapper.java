package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.MapSchool;
import com.kdt.dto.MapSchoolDTO;

@Mapper(componentModel = "spring")
public interface MapSchoolMapper extends GenericMapper<MapSchoolDTO, MapSchool>{

}
