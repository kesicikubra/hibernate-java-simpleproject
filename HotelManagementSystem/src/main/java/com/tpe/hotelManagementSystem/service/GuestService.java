package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Guest;

import java.util.List;

public interface GuestService {

    //Step 35e saveGuest
    Guest saveGuest();

    //Step 50d create findGuestById
    void findGustById(Long id);

    //Step 52d create deleteGuestById
    void deleteGuestId(Long id);

    //Step 53d create findAllGuest
    List<Guest> findAllGuest();
}
