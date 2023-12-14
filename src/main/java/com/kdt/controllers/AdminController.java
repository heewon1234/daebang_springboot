package com.kdt.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import com.kdt.domain.entities.NewEstate;
import com.kdt.domain.entities.NewMember;
import com.kdt.domain.entities.Visitor;
import com.kdt.dto.MemberDTO;
import com.kdt.dto.NewEstateDTO;
import com.kdt.dto.NewMemberDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.VisitorDTO;
import com.kdt.services.AgentService;
import com.kdt.services.MemberService;
import com.kdt.services.NewEstateService;
import com.kdt.services.NewMemberService;
import com.kdt.services.VisitorService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private VisitorService vServ;
	@Autowired
	private AgentService aServ;
	@Autowired
	private NewMemberService nServ;
	@Autowired
	private MemberService mServ;
	@Autowired
	private NewEstateService neServ;
	
	//회원관리
	@GetMapping("getMember")
	public ResponseEntity<List<MemberDTO>> getMember() {
		try {
			List<MemberDTO> dto = mServ.getMember();
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			e.printStackTrace(); 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
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
    @GetMapping("/openApi/{bsnmCmpnm}")
    public ResponseEntity<String> callExternalApi(@PathVariable String bsnmCmpnm) {
        String apiUrl = "https://api.vworld.kr/ned/data/getEBOfficeInfo";
        String authKey = "32313C80-CF6D-3E59-953F-930749A348A4";
        
        try {
            // 한글 부분을 URLEncoder로 인코딩하여 URL에 추가
            String encodedBsnmCmpnm = URLEncoder.encode(bsnmCmpnm, StandardCharsets.UTF_8.toString());

            // URL 및 파라미터 설정
            String urlString = apiUrl + "?key=" + authKey +
                    "&domain=http://localhost:3000" +
                    "&sttusSeCode=1" +
                    "&ldCode=44" +
                    "&format=json" +
                    "&bsnmCmpnm=" + encodedBsnmCmpnm +
                    "&pageSize=10" +
                    "&pageNo=1";

            // URL 객체 생성
            URL url = new URL(urlString);

            // HttpURLConnection을 이용한 요청 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            // 요청 전송
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 응답 처리
            BufferedReader in;
            StringBuilder response = new StringBuilder();
            if (responseCode >= 200 && responseCode < 300) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 응답 출력
            System.out.println("response.toString()"+response.toString());

            return ResponseEntity.ok().body(response.toString());
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
//    @GetMapping("/xyopenApi")
//    public ResponseEntity<String> callXYApi(@PathVariable String bsnmCmpnm) {
//        String apiUrl = "https://api.vworld.kr/ned/data/getEBOfficeInfo";
//        String authKey = "32313C80-CF6D-3E59-953F-930749A348A4";
//        
//        try {
//            // 한글 부분을 URLEncoder로 인코딩하여 URL에 추가
//            String encodedBsnmCmpnm = URLEncoder.encode(bsnmCmpnm, StandardCharsets.UTF_8.toString());
//
//            // URL 및 파라미터 설정
//            String urlString = apiUrl + "?key=" + authKey +
//                    "&domain=http://localhost:3000" +
//                    "&sttusSeCode=1" +
//                    "&ldCode=44" +
//                    "&format=json" +
//                    "&bsnmCmpnm=" + encodedBsnmCmpnm +
//                    "&pageSize=10" +
//                    "&pageNo=1";
//
//            // URL 객체 생성
//            URL url = new URL(urlString);
//
//            // HttpURLConnection을 이용한 요청 설정
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Content-Type", "application/json");
//
//            // 요청 전송
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            // 응답 처리
//            BufferedReader in;
//            StringBuilder response = new StringBuilder();
//            if (responseCode >= 200 && responseCode < 300) {
//                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            } else {
//                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//            }
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // 응답 출력
//            System.out.println("response.toString()"+response.toString());
//
//            return ResponseEntity.ok().body(response.toString());
//        } catch (IOException e) {
//            System.out.println("Exception: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }

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
  //회원 누적 방문자수
    @GetMapping("/newMember/sum")
    public ResponseEntity<Integer> newMember_sum() {
        int num = nServ.newMember_sum();
        return ResponseEntity.ok(num);
    }
  //오늘 회원등록수
    @GetMapping("/dailyMember")
    public ResponseEntity<NewMember> getDailyMember() {
        LocalDate today = LocalDate.now();
        NewMember dailyNewMember = nServ.getDailyNewMember(today);
        return ResponseEntity.ok(dailyNewMember);
    }
    
    @GetMapping("/agent/isEstateNumber/{number}")
    public ResponseEntity<Boolean> isEstateNumber(@PathVariable String number) {
        boolean isDuplicate = aServ.isEstateNumber(number);
        return ResponseEntity.ok(isDuplicate);
    }


    @PostMapping("/agent/signup")
    public ResponseEntity<Void> signup(RealEstateAgentDTO RealEstateAgentDTO) {
        aServ.signup(RealEstateAgentDTO);
        return ResponseEntity.ok().build();
    }
  //부동산 신규회원 등록 수
    @GetMapping("/agent/todayNewEstate")
    public ResponseEntity<NewEstateDTO> getTodayNewEstate() {
    	try {
    		NewEstateDTO dto = neServ.getTodayNewEstate();
            System.out.println(dto);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/agent/createNewEstate")
    public ResponseEntity<Void> createNewEstate() {
    	neServ.createNewEstate();
        return ResponseEntity.ok().build();
    }
    @PutMapping("/agent/incrementNewEstate/{seq}")
    public ResponseEntity<Void> incrementNewEstate(@PathVariable Long seq) {
        try {
        	neServ.incrementNewEstate(seq);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/agent/newEstate/getAll")
    public ResponseEntity<List<NewEstateDTO>> newEstateGetAll() {
    	List<NewEstateDTO> list = neServ.getAll();
    	return ResponseEntity.ok(list);
    }
  //부동산 누적 방문자수
    @GetMapping("/agent/sum")
    public ResponseEntity<Integer> agent_sum() {
        int num = neServ.newEstate_sum();
        return ResponseEntity.ok(num);
    }
  //오늘 공인중개사 등록수
    @GetMapping("/dailyEstate")
    public ResponseEntity<NewEstate> getDailyEstate() {
        LocalDate today = LocalDate.now();
        NewEstate dailyEstate = neServ.getDailyNewEstate(today);
        return ResponseEntity.ok(dailyEstate);
    }
}
