package com.kdt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kdt.domain.entities.EstateTemp;
import com.kdt.dto.EstateTempDTO;

@Mapper(componentModel = "spring")
public interface EstateTempMapper extends GenericMapper<EstateTempDTO,EstateTemp>{

	@Mapping(source = "room.roomType", target = "roomType")
    @Mapping(source = "structure.structureType", target = "structureType")
    @Mapping(source = "building.buildingType", target = "buildingType")
    @Mapping(source = "transaction.transactionType", target = "transactionType")
    @Mapping(source = "heatingSystem.heatingType", target = "heatingType")
    EstateTempDTO toDto(EstateTemp e);

	@Mapping(source = "roomType", target = "room.roomType")
    @Mapping(source = "structureType", target = "structure.structureType")
    @Mapping(source = "buildingType", target = "building.buildingType")
    @Mapping(source = "transactionType", target = "transaction.transactionType")
    @Mapping(source = "heatingType", target = "heatingSystem.heatingType")
    EstateTemp toEntity(EstateTempDTO d);
}
