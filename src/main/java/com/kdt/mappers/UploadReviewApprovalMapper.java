package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.UploadReviewApproval;
import com.kdt.dto.UploadReviewApprovalDTO;

@Mapper(componentModel = "spring")
public interface UploadReviewApprovalMapper extends GenericMapper<UploadReviewApprovalDTO, UploadReviewApproval> {

}
