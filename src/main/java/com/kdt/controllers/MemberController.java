package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MemberDTO;
import com.kdt.dto.NewMemberDTO;
import com.kdt.dto.UpdateMemberDTO;
import com.kdt.services.MemberService;
import com.kdt.services.NewMemberService;

@RestController
@RequestMapping("/api/member/")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService mServ;
	
	@Autowired
	private NewMemberService nServ;

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
		return ResponseEntity.ok(idList);

	}
	@GetMapping("myInfo/{id}")
	public ResponseEntity<MemberDTO> myInfo(@PathVariable String id) {
		try {
			MemberDTO dto = mServ.myInfo(id);
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("updateMyInfo")
	public ResponseEntity<Void> updateMyInfo(@RequestBody UpdateMemberDTO dto) {
		mServ.updateMyInfo(dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("changePw")
	public ResponseEntity<Void> changePw(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		mServ.changePw(id,pw);
		return ResponseEntity.ok().build();
	}
	//신규회원 등록 수
    @GetMapping("/todayNewMember")
    public ResponseEntity<NewMemberDTO> getTodayNewMamber() {
    	try {
    		NewMemberDTO dto = nServ.getTodayNewMamber();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
        	logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/createNewMember")
    public ResponseEntity<Void> createNewMember() {
    	nServ.createNewMamber();
        return ResponseEntity.ok().build();
    }
    @PutMapping("/incrementNewMember/{seq}")
    public ResponseEntity<Void> incrementNewMember(@PathVariable Long seq) {
        try {
        	nServ.incrementNewMember(seq);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
