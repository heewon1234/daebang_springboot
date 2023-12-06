package com.kdt.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.kdt.domain.entities.Member;

public class SecurityUser extends User{
	private String name;
	
	public SecurityUser(Member m) {
		super(m.getId(),m.getPw(),AuthorityUtils.createAuthorityList(m.getRole()));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
