package com.kdt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kdt.services.MemberSecurityService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MemberSecurityService mSecServ;
	
	@Bean
	protected SecurityFilterChain config(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		
		
		http.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/api/member/**")).authenticated()
		.requestMatchers(new AntPathRequestMatcher("/api/manager/**")).hasRole("MANAGER")
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();
		
		http.formLogin().loginProcessingUrl("/api/member/login") // 로그인 처리 url 윗윗줄에 안먹음
		.usernameParameter("id")
		.passwordParameter("pw")
		.successHandler((request, response, authentication) -> {
			System.out.println("로그인 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		})
		.failureHandler((request, response, exception) -> {
			System.out.println("로그인 실패");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		});
		
		http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 로그인안한 사용자가 접근할 때 네트워크 에러 대신 forbidden 띄워주기
		});
		
		http.logout().logoutUrl("/api/member/logout").invalidateHttpSession(true)
		.logoutSuccessHandler((request, response, authentication) -> {
			System.out.println("로그아웃 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		});
		http.userDetailsService(mSecServ);
		return http.build();
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
