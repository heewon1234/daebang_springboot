package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.MapRegion;
import com.kdt.dto.MapRegionDTO;

@Mapper(componentModel = "spring")
public interface MapRegionMapper extends GenericMapper<MapRegionDTO, MapRegion>{

}
