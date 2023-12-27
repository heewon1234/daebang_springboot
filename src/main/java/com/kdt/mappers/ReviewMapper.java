package com.kdt.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kdt.domain.entities.Review;
import com.kdt.dto.ReviewDTO;
import com.kdt.dto.UploadReviewDTO;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends GenericMapper<ReviewDTO,Review>{

	Review toEntity(UploadReviewDTO dto);

	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDTO(UploadReviewDTO dto, @MappingTarget Review review);
}
