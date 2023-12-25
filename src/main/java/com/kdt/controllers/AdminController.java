package com.kdt.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.kdt.dto.ReportDTO;
import com.kdt.dto.VisitorDTO;
import com.kdt.services.AgentService;
import com.kdt.services.EstateService;
import com.kdt.services.MemberService;
import com.kdt.services.NewEstateService;
import com.kdt.services.NewMemberService;
import com.kdt.services.ReportService;
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
	@Autowired
	private EstateService eServ;
	@Autowired
	private ReportService rServ;
	
	//신고
	@GetMapping("/selectAllByReportStatus")
	public ResponseEntity<List<ReportDTO>> selectAllByReportStatus() {
		List<ReportDTO> dtos = rServ.selectAllByReportStatus();
		return ResponseEntity.ok(dtos);
	}
	@GetMapping("/countByReportStatus")
	public ResponseEntity<Long> countByReportStatus() {
		Long count = rServ.countByReportStatus();
		return ResponseEntity.ok(count);
	}
	
	//원룸 투룸 별 수
	@GetMapping("/countByRoomCode")
	public ResponseEntity<List<Object[]>> countByRoomCode() {
		List<Object[]> count = eServ.countByRoomCode();
		return ResponseEntity.ok(count);
	}
	//전체 매물 수
	@GetMapping("/countEstate")
    public ResponseEntity<Long> countEstate() {
        Long count = eServ.countEstate();
        return ResponseEntity.ok(count);
    }
	//오늘 등록된 매물 수
	@GetMapping("/countTodayEstate")
	public ResponseEntity<Long> countTodayByEstate() {
		Long count = eServ.countTodayByEstate();
		return ResponseEntity.ok(count);
	}
	
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
	

    //오늘 방문자수
    @GetMapping("/dailyVisitors")
    public ResponseEntity<Visitor> getDailyVisitors() {
        LocalDate today = LocalDate.now();
        Visitor dailyVisitors = vServ.getDailyVisitors(today);
        return ResponseEntity.ok(dailyVisitors);
    }
    //어제 방문자수
    @GetMapping("/getYesterdayVisitors")
    public ResponseEntity<Visitor> getYesterdayVisitors() {
        LocalDate yesterday = LocalDate.now().minusDays(1); // 어제 날짜 구하기
        Visitor yesterdayVisitors = vServ.getYesterdayVisitors(yesterday);
        return ResponseEntity.ok(yesterdayVisitors);
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
    @GetMapping("/getYesterdayMember")
    public ResponseEntity<NewMember> getYesterdayMember() {
    	LocalDate yesterday = LocalDate.now().minusDays(1);
    	NewMember dailyNewMember = nServ.getYesterdayMember(yesterday);
    	return ResponseEntity.ok(dailyNewMember);
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
    @GetMapping("/getYesterdayNewEstate")
    public ResponseEntity<NewEstate> getYesterdayNewEstate() {
    	LocalDate yesterday = LocalDate.now().minusDays(1);
    	NewEstate dailyEstate = neServ.getDailyNewEstate(yesterday);
    	return ResponseEntity.ok(dailyEstate);
    }
}
