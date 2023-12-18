package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Review;
import com.kdt.dto.ReviewDTO;
import com.kdt.dto.UploadReviewDTO;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends GenericMapper<ReviewDTO,Review>{

	Review toEntity(UploadReviewDTO dto);
}
