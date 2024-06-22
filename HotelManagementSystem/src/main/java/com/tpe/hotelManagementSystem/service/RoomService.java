package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Room;

import java.util.List;

public interface RoomService {

    //Step 28a saveRoom
    Room saveRoom();

    //Step 39d create findRoomById
    Room findRoomById(Long id);

    //Step 40d create findAllRoom
    List<Room> findAllRooms();

    //Step 41d create deleteRoomById
    void deleteRoomById(Long id);
}
