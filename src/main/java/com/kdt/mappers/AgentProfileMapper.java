package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.AgentProfile;
import com.kdt.dto.AgentProfileDTO;

@Mapper(componentModel = "spring")
public interface AgentProfileMapper extends GenericMapper<AgentProfileDTO,AgentProfile>{

}
