package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Reservation;

import java.util.List;

public interface ReservationRepository {

    //Step 49a create saveReservation
    Reservation saveReservation(Reservation reservation);

    //Step 54a create findReservationbyId
    Reservation findReservationById(Long id);

    //Step 55a create findAllReservations
    List<Reservation> findAllReservations();

    //Step 56a create deleteReservationById
    void deleteReservationById(Long id);
}
