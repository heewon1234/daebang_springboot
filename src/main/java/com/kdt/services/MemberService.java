package com.kdt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.domain.entities.Member;
import com.kdt.dto.MemberDTO;
import com.kdt.dto.UpdateMemberDTO;
import com.kdt.mappers.MemberMapper;
import com.kdt.repositories.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository mRepo;
	
	@Autowired
	private MemberMapper mMapper;
	
	
	private final PasswordEncoder passwordEncoder;

    //@Autowired
    public MemberService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public Boolean idDuplCheck(MemberDTO dto) {
		Optional<Member> m = mRepo.findById(dto.getId());
		if (m.isPresent()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void signUp(MemberDTO dto) {
		dto.setRole("ROLE_MEMBER");
		dto.setEnabled(true);
		String hashedPassword = passwordEncoder.encode(dto.getPw());
		dto.setPw(hashedPassword);
		Member m = mMapper.toEntity(dto);
		mRepo.save(m);
	}
	//관리자 회원관리
	public List<MemberDTO> getMember(){
		List<Member> list = mRepo.findByRole("ROLE_MEMBER");
		List<MemberDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
	public void deleteById(String id) {
		Member e = mRepo.findById(id).get();
		mRepo.delete(e);
	}
	public List<MemberDTO> getId(String email){
		List<Member> list = mRepo.selectbyemail(email);
		List<MemberDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
	public List<MemberDTO> getDto(String id,String email){
		List<Member> list = mRepo.findByIdAndEmail(id,email);
		List<MemberDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
	
	public MemberDTO myInfo(String id) {
		Member m = mRepo.findById(id).get();
		MemberDTO mdto = mMapper.toDto(m);
		return mdto;
	}
	
	public void updateMyInfo(UpdateMemberDTO dto) {
		Member m = mRepo.findById(dto.getId()).get();
		MemberDTO mdto = new MemberDTO(m.getId(),m.getPw(),dto.getName(),dto.getPhone(),dto.getEmail(),dto.getZipcode(),dto.getAddress1(),dto.getAddress2(),null,m.getRole(),m.isEnabled());
		mMapper.updateEntityFromDTO(mdto, m);
		mRepo.save(m);
	}
	
	@Transactional
	public void changePw(String id, String pw) {
		String hashedPassword = passwordEncoder.encode(pw);
		mRepo.changePw(id, hashedPassword);
	}
	
	public void approve(String id) {
		Member e = mRepo.findById(id).get();
		e.setEnabled(true);
		mRepo.save(e);
	}
	public void revoke_approval(String id) {
		Member e = mRepo.findById(id).get();
		e.setEnabled(false);
		mRepo.save(e);
	}
	
}
