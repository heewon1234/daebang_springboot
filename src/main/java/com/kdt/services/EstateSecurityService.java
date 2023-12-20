package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.repositories.AgentRepository;
import com.kdt.security.SecurityUser;

@Service
public class EstateSecurityService implements UserDetailsService {
	@Autowired
	private AgentRepository aRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RealEstateAgent a = aRepo.findById(username).get();
		
		SecurityUser su = new SecurityUser(a);
		su.setName(a.getName());
		return su;
	}
}
