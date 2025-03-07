package com.room.roomMicroService.dto;

import java.math.BigDecimal;

import com.room.roomMicroService.model.RoomType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {
	
	public int roomId;
	
	public String roomNumberString;
	
	public RoomType type;
	
	public int capacity;
	
	public BigDecimal rentPerTenant;
	
	public int currentOccupancy;

}
