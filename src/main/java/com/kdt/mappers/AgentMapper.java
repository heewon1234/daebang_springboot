package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.dto.RealEstateAgentDTO;

@Mapper(componentModel = "spring")
public interface AgentMapper extends GenericMapper<RealEstateAgentDTO,RealEstateAgent> {

}
