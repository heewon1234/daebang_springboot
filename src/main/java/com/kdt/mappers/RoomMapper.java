package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Room;
import com.kdt.dto.RoomDTO;

@Mapper(componentModel = "spring")
public interface RoomMapper extends GenericMapper<RoomDTO,Room>{

}
