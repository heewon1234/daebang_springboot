package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.UploadEstateOption;
import com.kdt.dto.UploadEstateOptionDTO;

@Mapper(componentModel = "spring")
public interface UploadEstateOptionMapper extends GenericMapper<UploadEstateOptionDTO,UploadEstateOption>{

}
