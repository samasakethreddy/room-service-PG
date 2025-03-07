package com.room.roomMicroService.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.room.roomMicroService.dto.RoomRequest;
import com.room.roomMicroService.dto.RoomResponse;
import com.room.roomMicroService.model.Room;
import com.room.roomMicroService.model.RoomType;
import com.room.roomMicroService.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	
	public void addRoom(RoomRequest roomRequest) {
		Room room = Room.builder()
				.roomNumberString(roomRequest.getRoomNumberString())
				.type(roomRequest.getType())
				.capacity(roomRequest.getCapacity())
				.currentOccupancy(roomRequest.getCurrentOccupancy())
				.build();
		
		roomRepository.save(room);
	}

	
	public RoomResponse getRoom(Integer roomId) {
		
		Optional<Room> roomResponseOptional = roomRepository.findById(roomId);
		
		if(roomResponseOptional.isPresent()) {
			return mapToResponse(roomResponseOptional.get());
		}else {
			return null;
		}

	}

	public List<RoomResponse> getAllRooms() {
		
		List<Room> rooms= roomRepository.findAll();
		
		return rooms.stream().map(room -> mapToResponse(room)).toList();
		
	}
	
	
	@Transactional
	public RoomResponse updateRoom(Integer roomId, RoomRequest roomRequest) {
		
		int capacity = roomRequest.getCapacity();
		
		String roomNumberString = roomRequest.getRoomNumberString();

		RoomType type = roomRequest.getType();

		BigDecimal rentPerTenant = roomRequest.getRentPerTenant();

		int currentOccupancy = roomRequest.getCurrentOccupancy();
		
		roomRepository.updateRoom(roomId, roomNumberString, type, capacity, rentPerTenant, currentOccupancy);
		
		return getRoom(roomId);
	}

	
	
	private RoomResponse mapToResponse(Room room) {
		
		RoomResponse roomResponse = RoomResponse.builder().roomId(room.getRoomId())
				.roomNumberString(room.getRoomNumberString())
				.type(room.getType())
				.capacity(room.getCapacity())
				.rentPerTenant(room.getRentPerTenant())
				.currentOccupancy(room.getCurrentOccupancy())
				.build();
		
		return roomResponse;
		
	}
}
