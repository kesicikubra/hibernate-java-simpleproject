package com.tpe.hotelManagementSystem.exception;

public class GuestNotFoundException extends RuntimeException{
    public GuestNotFoundException(String message) {
        super(message);
    }
}
