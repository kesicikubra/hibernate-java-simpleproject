package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Hotel;

import java.util.List;

public interface HotelRepository {

    //Step 21 a create saveGotel method
    Hotel saveHotel(Hotel hotel);

    //Step 26a create findHotelById
    Hotel findHotelById(Long id);


    //Step 31a deleteHotelById
    void deleteHotelById(Long id);

    //Step 37a findAllHotels
    List<Hotel> findAllHotels();

    //Step 38a create updateHotel
    void updateHotel(Hotel hotel);

}
