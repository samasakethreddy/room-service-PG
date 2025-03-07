package com.room.roomMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class RoomMicroServiceApplication {
    public static void main(String[] args) {
        System.out.println("Room micro-service started...");
        SpringApplication.run(RoomMicroServiceApplication.class, args);
    }
}
