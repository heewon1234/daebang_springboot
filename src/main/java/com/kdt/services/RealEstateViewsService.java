package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.kdt.mappers.RealEstateViewsMapper;
import com.kdt.repositories.RealEstateViewsRepository;

public class RealEstateViewsService {
	@Autowired
	private RealEstateViewsRepository rRepo;
	
	@Autowired
	private RealEstateViewsMapper rMapper;
}
