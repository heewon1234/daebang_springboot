package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Board;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.BoardUploadDTO;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapper<BoardDTO,Board>{

	Board toEntity(BoardUploadDTO dto);
}
