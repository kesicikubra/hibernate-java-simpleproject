package com.tpe.hotelManagementSystem.exception;

public class RoomResourceNotFoundException extends RuntimeException{
    public RoomResourceNotFoundException(String message) {
        super(message);
    }
}
