package com.kdt.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.kdt.domain.entities.Member;
import com.kdt.domain.entities.Real_Estate_Agent;

public class SecurityUser extends User{
	private String name;
	
	public SecurityUser(Real_Estate_Agent a) {
        super(a.getEmail(), a.getPw(), a.isEnabled(), true, true, true, AuthorityUtils.createAuthorityList(a.getRole()));
    }
	public SecurityUser(Member m) {
		super(m.getId(),m.getPw(),m.isEnabled(),true,true,true,AuthorityUtils.createAuthorityList(m.getRole()));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
