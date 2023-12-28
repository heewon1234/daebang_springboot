package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entities.FavoriteBoard;
import com.kdt.dto.FavoriteBoardDTO;
import com.kdt.mappers.FavoriteBoardMapper;
import com.kdt.repositories.FavoriteBoardRepository;

@Service
public class FavoriteBoardService {
	
	@Autowired
	private FavoriteBoardRepository fbRepo;
	
	@Autowired
	private FavoriteBoardMapper fbMapper;
	
	public void inserFav(FavoriteBoardDTO dto) {
		FavoriteBoard fav = fbRepo.findByIdAndParentSeq(dto.getId(), dto.getParentSeq());
		if(fav == null) {
			fbRepo.save(fbMapper.toEntity(dto));
		}
	}
	
	public void delFav(String id, Long parentSeq) {

		FavoriteBoard fav = fbRepo.findByIdAndParentSeq(id, parentSeq);
		fbRepo.delete(fav);
	}

}
