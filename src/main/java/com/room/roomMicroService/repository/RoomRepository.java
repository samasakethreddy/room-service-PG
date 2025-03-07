package com.room.roomMicroService.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.room.roomMicroService.dto.RoomRequest;
import com.room.roomMicroService.model.Room;
import com.room.roomMicroService.model.RoomType;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

	@Modifying
    @Transactional
    @Query("UPDATE Room r SET r.roomNumberString = :roomNumberString, r.type = :type,"
    		+ "r.capacity = :capacity, r.rentPerTenant = :rentPerTenant, "
    		+ "r.currentOccupancy = :currentOccupancy "
    		+ "WHERE r.roomId = :roomId")
	void updateRoom(@Param("roomId") Integer roomId, 
            @Param("roomNumberString") String roomNumberString, 
            @Param("type") RoomType type,
            @Param("capacity") int capacity,
            @Param("rentPerTenant") BigDecimal rentPerTenant, 
            @Param("currentOccupancy") int currentOccupancy);

}


