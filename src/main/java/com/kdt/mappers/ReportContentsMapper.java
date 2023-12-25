package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.ReportContents;
import com.kdt.dto.ReportContentsDTO;

@Mapper(componentModel = "spring")
public interface ReportContentsMapper extends GenericMapper<ReportContentsDTO,ReportContents>{

}
