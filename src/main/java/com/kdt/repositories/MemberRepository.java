package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	List<Member> findByIdAndEmail(String id,String email);
	List<Member> findByRole(String role);
	
	@Query("select m from Member m where m.email = ?1")
	List<Member> selectbyemail(String email);

	@Modifying
	@Query("update Member m set m.pw=?2 where m.id=?1")
	int changePw(String id, String pw);
}
