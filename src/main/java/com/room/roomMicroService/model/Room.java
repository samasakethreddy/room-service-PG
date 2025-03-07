package com.room.roomMicroService.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int roomId;
	
	public String roomNumberString;
	
	@Enumerated(EnumType.STRING)
	public RoomType type;
	
	public int capacity;
	
	public BigDecimal rentPerTenant;
	
	public int currentOccupancy;
	
}
