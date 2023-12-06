package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Board;
import com.kdt.dto.BoardDTO;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapper<BoardDTO,Board>{

}
