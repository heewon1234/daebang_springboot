package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Real_Estate_Agent;
import com.kdt.dto.RealEstateAgentDTO;

@Mapper(componentModel = "spring")
public interface AgentMapper extends GenericMapper<RealEstateAgentDTO,Real_Estate_Agent> {

}
