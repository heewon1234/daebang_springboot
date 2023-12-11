package com.kdt.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kdt.domain.entities.Visitor;
import com.kdt.dto.NewMemberDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.VisitorDTO;
import com.kdt.services.AgentService;
import com.kdt.services.NewMemberService;
import com.kdt.services.VisitorService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private VisitorService vServ;
	@Autowired
	private AgentService aServ;
	@Autowired
	private NewMemberService nServ;
	
	//공인중개사 관련
	@GetMapping("/agent/getAll")
    public ResponseEntity<List<RealEstateAgentDTO>> getAll() {
    	try {
    		List<RealEstateAgentDTO> dto = aServ.getAll();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	@DeleteMapping("/agent/delete/{email}")
	public ResponseEntity<Void> deleteById(@PathVariable String email){
		aServ.deleteById(email);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/agent/approve/{email}")
    public ResponseEntity<Void> approve(@PathVariable String email) {
        try {
        	aServ.approve(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	@PutMapping("/agent/revoke-approval/{email}")
	public ResponseEntity<Void> revoke_approval(@PathVariable String email) {
		try {
			aServ.revoke_approval(email);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/userip")
    public String getUserIP(HttpServletRequest request) {
        String userIP = request.getHeader("X-Forwarded-For");
        if (userIP == null || userIP.isEmpty()) {
            userIP = request.getRemoteAddr();
        }
        System.out.println(userIP);
        return "User IP Address: " + userIP;
    }
	//방문자수
    @GetMapping("/todayVisitor")
    public ResponseEntity<VisitorDTO> getTodayVisitor() {
    	try {
            VisitorDTO dto = vServ.getTodayVisitor();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/createVisitor")
    public ResponseEntity<Void> createVisitor() {
        vServ.createVisitor();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/incrementVisitor/{seq}")
    public ResponseEntity<Void> incrementVisitor(@PathVariable Long seq) {
        try {
            vServ.incrementVisitor(seq);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //오늘 방문자수
    @GetMapping("/dailyVisitors")
    public ResponseEntity<Visitor> getDailyVisitors() {
        LocalDate today = LocalDate.now();
        Visitor dailyVisitors = vServ.getDailyVisitors(today);
        return ResponseEntity.ok(dailyVisitors);
    }
    //모든 방문자수
    @GetMapping("/visitors/getAll")
    public ResponseEntity<List<VisitorDTO>> visitorsGetAll() {
    	List<VisitorDTO> list = vServ.getAll();
    	return ResponseEntity.ok(list);
    }
    //누적 방문자수
    @GetMapping("/visitors/sum")
    public ResponseEntity<Integer> sum() {
        int num = vServ.sum();
        return ResponseEntity.ok(num);
    }

    
    @GetMapping("/monthlyVisitors")
    public ResponseEntity<List<Visitor>> getMonthlyVisitors() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
        List<Visitor> monthlyVisitors = vServ.getMonthlyVisitors(startOfMonth, endOfMonth);
        return ResponseEntity.ok(monthlyVisitors);
    }
    @GetMapping("/openApi")
    public String callExternalApi() {
        // 외부 API의 URL
        String apiUrl = "http://openapi.nsdi.go.kr/nsdi/EstateBrkpgService/attr/getEBOfficeInfo";

        // 인증키 및 다른 필요한 매개변수
        String authKey = "2ec1311cbdddd7356ed72a";
        // 필요한 다른 매개변수들을 추가해주세요

        // 외부 API 호출을 위한 RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // 외부 API 호출 및 응답 받기
        String result = restTemplate.getForObject(apiUrl + "?authkey=" + authKey, String.class);
System.out.println(result);
        // 외부 API 응답 반환
        return result;
    }
    //신규회원 등록 수
    @GetMapping("/todayNewMember")
    public ResponseEntity<NewMemberDTO> getTodayNewMamber() {
    	try {
    		NewMemberDTO dto = nServ.getTodayNewMamber();
            System.out.println(dto);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
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
    @GetMapping("/newMember/getAll")
    public ResponseEntity<List<NewMemberDTO>> newMemberGetAll() {
    	List<NewMemberDTO> list = nServ.getAll();
    	return ResponseEntity.ok(list);
    }
}
