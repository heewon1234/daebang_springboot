package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.VisitorDTO;
import com.kdt.services.VisitorService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/visit")
public class VisitorController {
	@Autowired
	private VisitorService vServ;
	@GetMapping("/test")
    public ResponseEntity<Void> getTodayVisitor(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            String sessionId = session.getId();

            Cookie[] cookies = request.getCookies();
            boolean visited = false;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("visited") && cookie.getValue().equals(sessionId)) {
                        visited = true;
                        break;
                    }
                }
            }

            VisitorDTO dto = vServ.getTodayVisitor();

            if (dto == null) {
                // 오늘 방문자 데이터가 없는 경우
                if (!visited) {
                    // 클라이언트가 처음 접속한 경우
                    Cookie visitedCookie = new Cookie("visited", sessionId);
                    visitedCookie.setMaxAge(24 * 60 * 60); // 쿠키 유효기간 설정 (24시간)
                    visitedCookie.setHttpOnly(true);
                    response.addCookie(visitedCookie);
                    vServ.createVisitor();
                    visitedCookie.setSecure(true);
                }
            } else {
                // 오늘 방문자 데이터가 있는 경우
                if (!visited) {
                    // 클라이언트가 처음 접속한 경우
                    Cookie visitedCookie = new Cookie("visited", sessionId);
                    visitedCookie.setMaxAge(24 * 60 * 60); // 쿠키 유효기간 설정 (24시간)
                    visitedCookie.setHttpOnly(true);//XSS로부터 보호
                    response.addCookie(visitedCookie);
                    vServ.incrementVisitor(dto.getSeq());
                    visitedCookie.setSecure(true);// HTTPS 프로토콜에서만
                }
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




		//	@GetMapping("/test")
		//    public String checkVisitor(HttpServletRequest request) {
		//        HttpSession session = request.getSession(true);
		//        String sessionId = session.getId();
		//        return "Session ID: " + sessionId;
		//    }
		//	//방문자수
		@GetMapping("/todayVisitor")
		public ResponseEntity<VisitorDTO> getTodayVisitor(HttpServletRequest request) {
			try {
				HttpSession session = request.getSession(true);
				String sessionId = session.getId();
				System.out.println(sessionId);
				VisitorDTO dto = vServ.getTodayVisitor();
				return ResponseEntity.ok(dto);
			} catch (Exception e) {
				// 예외가 발생한 경우 처리
				e.printStackTrace(); // 또는 로깅하여 예외 정보 기록
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		//    
		//    @PostMapping("/createVisitor")
		//    public ResponseEntity<Void> createVisitor() {
		//        vServ.createVisitor();
		//        return ResponseEntity.ok().build();
		//    }
		//
		//    @PutMapping("/incrementVisitor/{seq}")
		//    public ResponseEntity<Void> incrementVisitor(@PathVariable Long seq) {
		//        try {
		//            vServ.incrementVisitor(seq);
		//            return ResponseEntity.ok().build();
		//        } catch (Exception e) {
		//            // 예외 처리
		//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		//        }
		//    }
	}
