package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Guest;

import java.util.List;

public interface GuestRepository {

    //Step 34a
    void saveGuest(Guest guest);

    //Step 50a create findGuestById
    Guest findGuestById(Long id);

    //Step 52a create deleteGuestById
    void deleteGuestById(Long id);

    //Step 53a create findAllGuest
    List<Guest> findAllGuest();
}
