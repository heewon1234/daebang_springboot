package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.ReportStatus;
import com.kdt.dto.ReportStatusDTO;

@Mapper(componentModel = "spring")
public interface ReportStatusMapper extends GenericMapper<ReportStatusDTO,ReportStatus> {

}
