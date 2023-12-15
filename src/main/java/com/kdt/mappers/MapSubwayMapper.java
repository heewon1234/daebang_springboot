package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.NewMember;
import com.kdt.dto.MapSubwayDTO;

@Mapper(componentModel = "spring")
public interface MapSubwayMapper extends GenericMapper<MapSubwayDTO, NewMember>{

}
