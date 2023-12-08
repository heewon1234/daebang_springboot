package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MemberDTO;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/member/")
public class MemberController {

	@Autowired
	private MemberService mServ;
	
	@PostMapping("idDuplCheck")
	public ResponseEntity<Boolean> idDuplCheck(@RequestBody MemberDTO dto) {
		Boolean isdupl = mServ.idDuplCheck(dto);
		return ResponseEntity.ok(isdupl);
	}
	
	@PostMapping("signUp")
	public ResponseEntity<Void> login(@RequestBody MemberDTO dto) {
		mServ.signUp(dto);
		return ResponseEntity.ok().build();
	}
	@GetMapping("getAll")
    public ResponseEntity<List<MemberDTO>> getAll() {
    	try {
    		List<MemberDTO> dto = mServ.getAll();
            System.out.println(dto);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		mServ.deleteById(id);
		return ResponseEntity.ok().build();
	}
	@GetMapping("findId/{email}")
	public ResponseEntity<List<String>> getId(@PathVariable String email){
		List<MemberDTO> dto = mServ.getId(email);
		List<String> idList = new ArrayList<>();
		if(!(dto.size()==0)){
		for(int i =0;i<dto.size();i++) {
			idList.add(dto.get(i).getId());
		}
		}
		System.out.println(idList);
		return ResponseEntity.ok(idList);
		
	}
}
