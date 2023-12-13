package com.kdt.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kdt.domain.entities.Board;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.BoardUploadDTO;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapper<BoardDTO,Board>{

	Board toEntity(BoardUploadDTO dto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDTO(BoardUploadDTO dto, @MappingTarget Board Board);
}
