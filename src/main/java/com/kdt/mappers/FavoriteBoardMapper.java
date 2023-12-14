package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.FavoriteBoard;
import com.kdt.dto.FavoriteBoardDTO;

@Mapper(componentModel = "spring")
public interface FavoriteBoardMapper extends GenericMapper<FavoriteBoardDTO,FavoriteBoard>{

}
