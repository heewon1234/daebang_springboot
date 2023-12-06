package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
