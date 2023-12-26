package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.RealEstateViews;
import com.kdt.dto.RealEstateViewsDTO;

@Mapper(componentModel = "spring")
public interface RealEstateViewsMapper extends GenericMapper<RealEstateViewsDTO,RealEstateViews>{

}
