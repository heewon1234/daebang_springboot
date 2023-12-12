package com.kdt.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kdt.domain.entities.Visitor;
import com.kdt.dto.NewMemberDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.VisitorDTO;
import com.kdt.services.AgentService;
import com.kdt.services.MemberService;
import com.kdt.services.NewMemberService;
import com.kdt.services.VisitorService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	@Autowired
	private VisitorService vServ;
	@Autowired
	private AgentService aServ;
	@Autowired
	private NewMemberService nServ;
	@Autowired
	private MemberService mServ;
	
	
	
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
	//member ban
	@PutMapping("/member/approve/{id}")
    public ResponseEntity<Void> mapprove(@PathVariable String id) {
        try {
        	mServ.approve(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	@PutMapping("/member/revoke-approval/{id}")
	public ResponseEntity<Void> mrevoke_approval(@PathVariable String id) {
		try {
			mServ.revoke_approval(id);
			System.out.println(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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
//    @GetMapping("/openApi")
//    public ResponseEntity<String> getExternalApiUrl() {
//        // 외부 API의 URL
//        String apiUrl = "http://api.vworld.kr/ned/data/getEBOfficeInfo";
//
//        // 인증키 및 다른 필요한 매개변수
//        String authKey = "32313C80-CF6D-3E59-953F-930749A348A4";
//        // UriComponentsBuilder를 사용하여 URL 및 매개변수 구성
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
//                .queryParam("key", authKey)
//                .queryParam("domain", "http://localhost:3000")
//                .queryParam("ldCode", "44")//"44131"
//                .queryParam("numOfRows", "100")
//                .queryParam("format", "json");
//
//        return ResponseEntity.ok().body(builder.toUriString());
//    }

//    @GetMapping("/openApi")
//    public ResponseEntity<String> callExternalApi(
//            @RequestParam int pageNo,
//            @RequestParam int pageSize,
//            @RequestParam String bsnmCmpnm
//    ) {
//        String apiUrl = "http://api.vworld.kr/ned/data/getEBOfficeInfo";
//        String authKey = "32313C80-CF6D-3E59-953F-930749A348A4";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // UriComponentsBuilder를 사용하여 URL 및 매개변수 구성
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
//                .queryParam("key", authKey)
//                .queryParam("domain", "http://localhost:3000")
////                .queryParam("ldCode", "44")
//                .queryParam("sttusSeCode", "1")
//                .queryParam("format", "json")
//                .queryParam("bsnmCmpnm", bsnmCmpnm)
//                .queryParam("numOfRows", pageSize)
//                .queryParam("pageNo", pageNo);
//
//        try {
//            ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
//            System.out.println(response.getBody());
//
//            return ResponseEntity.ok().body(response.getBody());
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                 .body("An error occurred while calling the external API");
//        }
//    }
    @GetMapping("/openApi")
    public ResponseEntity<String> callExternalApi() {
        String apiUrl = "http://api.vworld.kr/ned/data/getEBOfficeInfo";
        String authKey = "32313C80-CF6D-3E59-953F-930749A348A4";

        RestTemplate restTemplate = new RestTemplate();
        String bsnmCmpnm = "에이스 부동산";

        // 한글 부분을 URLEncoder로 인코딩하여 URL에 추가
        String encodedBsnmCmpnm = URLEncoder.encode(bsnmCmpnm, StandardCharsets.UTF_8);
        System.out.println(encodedBsnmCmpnm);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("key", authKey)
                .queryParam("domain", "http://localhost:3000")
                .queryParam("sttusSeCode", "1")
                .queryParam("format", "json")
                .queryParam("bsnmCmpnm", encodedBsnmCmpnm)
                .queryParam("pageSize", "10")
                .queryParam("pageNo", "1");

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
            System.out.println(builder.toUriString());

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println(response.getBody());
                return ResponseEntity.ok().body(response.getBody());
            } else {
                System.out.println("HTTP Error: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody());
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
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
