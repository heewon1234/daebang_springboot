package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.MyReport;
import com.kdt.dto.MyReportDTO;

@Mapper(componentModel = "spring")
public interface MyReportMapper extends GenericMapper<MyReportDTO, MyReport>{

}
