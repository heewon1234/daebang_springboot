package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.HeatingSystem;
import com.kdt.dto.HeatingSystemDTO;

@Mapper(componentModel = "spring")
public interface HeatingSystemMapper extends GenericMapper<HeatingSystemDTO,HeatingSystem>{

}
