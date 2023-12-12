package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Board;
import com.kdt.domain.entities.UploadEstate;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.UploadEstateDTO;

@Mapper(componentModel = "spring")
public interface UploadEstateMapper extends GenericMapper<UploadEstateDTO,UploadEstate>{

}
