package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.MapSubway;
import com.kdt.dto.MapSubwayDTO;

@Mapper(componentModel = "spring")
public interface MapSubwayMapper extends GenericMapper<MapSubwayDTO, MapSubway>{

}
