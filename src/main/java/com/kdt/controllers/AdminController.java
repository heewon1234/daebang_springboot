package com.kdt.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.domain.entities.NewEstate;
import com.kdt.domain.entities.NewMember;
import com.kdt.domain.entities.Visitor;
import com.kdt.dto.EstateDTO;
import com.kdt.dto.MemberDTO;
import com.kdt.dto.NewEstateDTO;
import com.kdt.dto.NewMemberDTO;
import com.kdt.dto.RealEstateAgentDTO;
import com.kdt.dto.RealEstateViewsDTO;
import com.kdt.dto.ReportDTO;
import com.kdt.dto.ReviewApprovalDTO;
import com.kdt.dto.VisitorDTO;
import com.kdt.services.AgentService;
import com.kdt.services.EstateService;
import com.kdt.services.MemberService;
import com.kdt.services.NewEstateService;
import com.kdt.services.NewMemberService;
import com.kdt.services.RealEstateViewsService;
import com.kdt.services.ReportService;
import com.kdt.services.ReviewApprovalService;
import com.kdt.services.VisitorService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

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
	@Autowired
	private RealEstateViewsService rvServ;

	@Autowired
	private ReviewApprovalService raServ;
	//문의관리
	//관리자 승인
	@GetMapping("/reviewApproval/selectByAdmin")
	public ResponseEntity<List<ReviewApprovalDTO>> selectByAdmin() {
		List<ReviewApprovalDTO> list = raServ.selectByAdmin();
		return ResponseEntity.ok(list);
	}
	@PutMapping("/reviewApproval/revoke-approval/{seq}")
	public ResponseEntity<Void> revoke(@PathVariable Long seq) {
		raServ.revoke_approval(seq);

		logger.debug("번호"+Long.toString(seq));
		return ResponseEntity.ok().build();
	}

	@PutMapping("/reviewApproval/approval/{seq}")
	public ResponseEntity<Void> approval(@PathVariable Long seq) {
		raServ.approval(seq);

		return ResponseEntity.ok().build();
	}
	@PutMapping("/reviewApproval/return/{seq}")
	public ResponseEntity<Void> back(@PathVariable Long seq) {
		raServ.back(seq);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/reviewApproval/finalBack/{seq}")
	public ResponseEntity<Void> finalBack(@PathVariable Long seq) {
		raServ.finalBack(seq);
		return ResponseEntity.ok().build();
	}

	//매물관리
	@GetMapping("/estate/selectAll")
	public ResponseEntity<List<EstateDTO>> findAll() {
		List<EstateDTO> list = eServ.selectAll();

		return ResponseEntity.ok(list);
	}
	@DeleteMapping("/estate/delete/{estateId}")
	public ResponseEntity<Void> delete(@PathVariable Long estateId) throws Exception {
		eServ.deleteById(estateId);
		return ResponseEntity.ok().build();
	}

	//신고 전체 내용
	@DeleteMapping("/report/delete/{seq}")
	public ResponseEntity<Void> report_delete(@PathVariable Long seq) throws Exception {
		System.out.println("신고"+seq);
		logger.debug("신고"+seq);
		rServ.deleteBySeq(seq);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/report/selectAll")
	public ResponseEntity<List<ReportDTO>> selectAll() {
		List<ReportDTO> list = rServ.selectAllByWriteDateDESC();
		return ResponseEntity.ok(list);
	}
	@PutMapping("/estate/report/approve/{seq}")
	public ResponseEntity<Void> estateApprove(@PathVariable Long seq) {
		System.out.println("승인 : " + seq);
		logger.debug("승인 : " + seq);
		try {
			rServ.approve(seq);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/estate/report/revoke-approval/{seq}")
	public ResponseEntity<Void> estateRevoke_approval(@PathVariable Long seq) {
		try {
			rServ.revoke_approval(seq);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	//거부
	@PutMapping("/estate/report/reject/{seq}")
	public ResponseEntity<Void> reject(@PathVariable Long seq) {
		System.out.println("승인 : " + seq);
		try {
			rServ.reject(seq);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/estate/report/revoke-rejection/{seq}")
	public ResponseEntity<Void> revoke_rejection(@PathVariable Long seq) {
		try {
			rServ.revoke_rejection(seq);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	//매물 top5개
	@GetMapping("/topFive")
	public ResponseEntity<List<RealEstateViewsDTO>> topFive() {
		List<RealEstateViewsDTO> dtos = rvServ.topFive();
		return ResponseEntity.ok(dtos);
	}

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
			List<RealEstateAgentDTO> dto = aServ.getAllDESC();
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			// 예외가 발생한 경우 처리
			logger.error(e.getMessage());
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
			logger.debug(id);
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
	@ExceptionHandler
	public ResponseEntity<String> excetion(Exception e){
		e.printStackTrace();
		logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제할 대상이 존재하지 않습니다.");
	}
}
