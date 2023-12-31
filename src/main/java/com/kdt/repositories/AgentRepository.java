package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entities.RealEstateAgent;

public interface AgentRepository extends JpaRepository<RealEstateAgent, String>  {
	RealEstateAgent findByEstateNumber(String number);
	
	@Modifying
	@Query("update RealEstateAgent a set a.pw=?2 where a.email=?1")
	int changePw(String email, String pw);
	
	@Query("select m from RealEstateAgent m where m.name = ?1 and m.phone = ?2")
	List<RealEstateAgent> selectbynamephone(String name,String phone);
	
	@Query("select m.report_Count from RealEstateAgent m where m.estateNumber=?1")
	Long selectReportCountByEstateNumber(String estateNumber);
	
	@Query("select m.estateName from RealEstateAgent m where m.email = ?1")
	String findNamebyId(String estateid);
	
	@Modifying
	@Query("update RealEstateAgent a set a.manners_temperature=?1 where a.estateNumber=?2")
	void updateMannerTemp(double avgScore, String estateNumber);
	
	List<RealEstateAgent> findAllByEmail(String email);
	
	RealEstateAgent findByEmail(String email);
	
	List<RealEstateAgent> findByNameAndPhoneAndEmail(String name,String phone,String email);
	
	List<RealEstateAgent> findAllByOrderBySignupDateDesc();
}
