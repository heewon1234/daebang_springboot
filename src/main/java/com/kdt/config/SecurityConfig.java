package com.kdt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kdt.controllers.EstateAgentController;
import com.kdt.services.EstateSecurityService;
import com.kdt.services.MemberSecurityService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private MemberSecurityService mSecServ;
	
	@Autowired
	private EstateSecurityService eSecServ;
	
	@Bean
	protected SecurityFilterChain memberConfig(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.securityMatcher("/api/member/**");
		
		http.userDetailsService(mSecServ)
		.formLogin().loginPage("/login").loginProcessingUrl("/api/member/login") // 로그인 처리 url 윗윗줄에 안먹음
		.usernameParameter("id")
		.passwordParameter("pw")
		.successHandler((request, response, authentication) -> {
			logger.debug("로그인 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		})
		.failureHandler((request, response, exception) -> {
			logger.debug("로그인 실패");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		});
		
		http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 로그인안한 사용자가 접근할 때 네트워크 에러 대신 forbidden 띄워주기
		});
		
		http.logout().logoutUrl("/api/member/logout").invalidateHttpSession(true)
		.logoutSuccessHandler((request, response, authentication) -> {
			logger.debug("로그아웃 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		});
		
		return http.build();
	}
	
	@Bean
	protected SecurityFilterChain estateConfig(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.securityMatcher("/api/estate/**");
		
		http.userDetailsService(eSecServ)
		.formLogin().loginProcessingUrl("/api/estate/login") // 로그인 처리 url 윗윗줄에 안먹음
		.usernameParameter("id")
		.passwordParameter("pw")
		.successHandler((request, response, authentication) -> {
			logger.debug("로그인 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		})
		.failureHandler((request, response, exception) -> {
			logger.debug("로그인 실패");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		});
		
		http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 로그인안한 사용자가 접근할 때 네트워크 에러 대신 forbidden 띄워주기
		});
		
		http.logout().logoutUrl("/api/member/logout").invalidateHttpSession(true)
		.logoutSuccessHandler((request, response, authentication) -> {
			logger.debug("로그아웃 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		});
		
		return http.build();
	}
	
	@Bean
	protected SecurityFilterChain rightConfig(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/api/member/updateMyInfo")).hasAnyRole("ADMIN","MEMBER")
		.requestMatchers(new AntPathRequestMatcher("/api/member/changePw")).hasAnyRole("ADMIN","MEMBER")
		.requestMatchers(new AntPathRequestMatcher("/api/member/delete/**")).hasAnyRole("ADMIN","MEMBER")
		.requestMatchers(new AntPathRequestMatcher("/api/member/myInfo/**")).hasAnyRole("ADMIN","MEMBER")
		.requestMatchers(new AntPathRequestMatcher("/api/member/getAll")).hasRole("ADMIN")
		.requestMatchers(new AntPathRequestMatcher("/api/reviewApproval/admin/**")).hasRole("ADMIN")
		.requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();
		
		return http.build();
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
