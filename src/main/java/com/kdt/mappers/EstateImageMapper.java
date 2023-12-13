package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.EstateImage;
import com.kdt.dto.EstateImageDTO;

@Mapper(componentModel = "spring")
public interface EstateImageMapper extends GenericMapper<EstateImageDTO,EstateImage>{

}
