package com.kdt.mappers;

import org.mapstruct.Mapper;

import com.kdt.domain.entities.Transaction;
import com.kdt.dto.TransactionDTO;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends GenericMapper<TransactionDTO,Transaction>{

}
