package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.ReviewApproval;
import com.kdt.dto.ReviewApprovalDTO;

@Mapper(componentModel = "spring")
public interface ReviewApprovalMapper extends GenericMapper<ReviewApprovalDTO, ReviewApproval> {

}
