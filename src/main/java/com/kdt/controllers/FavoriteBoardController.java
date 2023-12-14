package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.FavoriteBoardDTO;
import com.kdt.security.SecurityUser;
import com.kdt.services.FavoriteBoardService;

@RestController
@RequestMapping("/api/favoriteBoard")
public class FavoriteBoardController {
	
	private SecurityUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null && auth.getPrincipal() instanceof SecurityUser) {
			SecurityUser su = (SecurityUser)auth.getPrincipal();
			return su;
		}
		return null;
	}
	
	@Autowired
	private FavoriteBoardService fServ;
	
	@PostMapping
	public ResponseEntity<Void> inserFav(@RequestBody FavoriteBoardDTO dto){
		dto.setId(getUser().getUsername());
		fServ.inserFav(dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{parentSeq}")
	public ResponseEntity<Void> delFav(@PathVariable Long parentSeq){
		fServ.delFav(getUser().getUsername(), parentSeq);
		return ResponseEntity.ok().build();
	}

}
