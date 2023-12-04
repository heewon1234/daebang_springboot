package com.kdt.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

// generic에 들어오는건 dataType임
// interface나 클래스 이름에 <> 해놓는건 generic ex> public class abc<> -> generic
// new 할 떄 <>는 generic parameter ex> List<MembersDTO> list -> generic parameter


//D : DTO / E : Entity ( 이름 바꿔도 됨 )

public interface GenericMapper<D,E>{
	
	D toDto(E e); // Entity -> DTO
	E toEntity(D d);
	
	List<D> toDTOLIst(List<E> e);
	List<E> toEntityList(List<D> d);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDTO(D d, @MappingTarget E e);
}
