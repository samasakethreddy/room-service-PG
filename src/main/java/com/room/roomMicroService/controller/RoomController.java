package com.room.roomMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.roomMicroService.dto.RoomRequest;
import com.room.roomMicroService.dto.RoomResponse;
import com.room.roomMicroService.service.RoomService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;

	@GetMapping("/test")
	public String name() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Extract roles
		String roles = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(", "));

		System.out.println("🔹 Current User Roles: " + roles); // Logs roles in the console

		return "test successful...";
	}
	
	@PostMapping
	public String name(@RequestBody RoomRequest roomRequest) {
		roomService.addRoom(roomRequest);
		return "Room added successfuly...";
	}


	@PreAuthorize("hasRole('TENANT')")
	@GetMapping("{roomId}")
	public RoomResponse getRoom(@PathVariable("roomId") Integer roomId) {
		return roomService.getRoom(roomId);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('OWNER')")
	public List<RoomResponse> getAllRooms() {
		return roomService.getAllRooms();
	}
	
	@PutMapping("/{roomId}")
	public RoomResponse updateRoom(@PathVariable("roomId") Integer roomId, @RequestBody RoomRequest roomRequest) {
		return roomService.updateRoom(roomId, roomRequest);
	}

}
