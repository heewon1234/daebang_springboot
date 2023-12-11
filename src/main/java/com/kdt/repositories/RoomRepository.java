package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.EstateImage;
import com.kdt.domain.entities.Room;

public interface RoomRepository extends JpaRepository<Room, String>{
}
