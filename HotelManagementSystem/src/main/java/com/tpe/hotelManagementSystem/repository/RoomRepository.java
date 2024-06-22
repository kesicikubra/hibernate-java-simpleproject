package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Room;

import java.util.List;

public interface RoomRepository {

    //Step 27a saveRoom
    Room saveRoom(Room room);

    //Step 39a create findRoomById
    Room findRoomById(Long roomId);


    //Step 40a create findAllRoom
    List<Room> findAllRoom();

    //Step 41a create deleteRoomById
    void deleteRoomById(Long id);


}
