package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Reservation;

import java.util.List;

public interface ReservationService {

    //Step 49e create saveReservation
    Reservation saveReservation();

    //tep 54d create findReservationbyId
    void findReservationById(Long id);

    //Step 55d create findAllReservations
    List<Reservation> findAllReservations();

    //Step 56d create deleteReservationById
    void deleteReservationById(Long id);

}
