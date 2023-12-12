package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Files;

public interface FileRepository extends JpaRepository<Files, Long>{

	@Query("select f.sysName as sys_name from Files f where f.parentSeq = ?1")
	String[] findSysNameByParentSeq(Long parentSeq);

}
