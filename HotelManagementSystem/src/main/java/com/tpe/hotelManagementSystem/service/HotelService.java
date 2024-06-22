package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;

import java.util.List;

public interface HotelService {

    //Step 23a create saveHotel method
    Hotel saveHotel();

    //Step 26c create findHotelById method
    Hotel findHotelById(Long id);

    //step 32a deleteHotelById
    void deleteHotelById(Long id);

    //Step 37d create findAllHotels
    List<Hotel> findAllHotels();

    //Step 38d create updateHotel
    void updateHotel(Long id,Hotel updateHotel);
}
