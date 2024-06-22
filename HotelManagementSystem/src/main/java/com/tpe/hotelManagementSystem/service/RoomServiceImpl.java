package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.domain.Room;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import com.tpe.hotelManagementSystem.exception.RoomResourceNotFoundException;
import com.tpe.hotelManagementSystem.repository.HotelRepository;
import com.tpe.hotelManagementSystem.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomServiceImpl implements RoomService {

    private static Scanner scanner;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
    //Step 29 write saveRoom codes
    @Override
    public Room saveRoom() {
        scanner = new Scanner(System.in);
        Room room = new Room();  //oda bilgilerini kaydetmek için boş bir oda objesi oluşturduk
        System.out.print("Enter room Id: ");//kaydedilecek oda bilgileri alındı
        room.setId(scanner.nextLong());
        scanner.nextLine();
        System.out.print("Enter room number: ");
        room.setNumber(scanner.nextLine());
        System.out.print("Enter room capacity: ");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter hotel id: "); //hangi hotelin odası olacağı soruldu
        Long hotelId = scanner.nextLong();
        scanner.nextLine();

        try {
            Hotel existingHotel = hotelRepository.findHotelById(hotelId);
            if (existingHotel == null) { //id ye ait hotel varlığı kontrol edildi
                throw new HotelResourceNotFoundException("Hotel not found with id : " + hotelId);
            } else {
                room.setHotel(existingHotel);  //odaya ait hotel verildi
                Room savedRoom = roomRepository.saveRoom(room); //oda kaydedildi
                existingHotel.getRooms().add(savedRoom); //hotele ait oda listeleri eklendi
                System.out.println("Room saved successfully. Room id " + savedRoom.getId());
            }
        } catch (HotelResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return room;
    }

    //Step 39f write findRoomById codes
    @Override
    public Room findRoomById(Long id) {
        try {
            Room foundRoom = roomRepository.findRoomById(id);
            if (foundRoom != null) {
                System.out.println("---------------------------------");
                System.out.println(foundRoom);

                return foundRoom;
            } else {
                throw new RoomResourceNotFoundException(" Room  not found with ID: " + id);
            }
        } catch (RoomResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Step 40f write findAllRoom codes
    @Override
    public List<Room> findAllRooms() {
        try {
            List<Room> rooms = roomRepository.findAllRoom();
            if (!rooms.isEmpty()) {
                for (Room room : rooms) {
                    System.out.println(room);
                }
            } else {
                throw new RuntimeException("No rooms found.");
            }
            return rooms;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>(); // Return boş bir ArrayList
        }
    }

    //Step 41f write eleteRoomById codes
    @Override
    public void deleteRoomById(Long id) {
        try {
            Room existingRoom = roomRepository.findRoomById(id);
            if (existingRoom == null) {
                throw new RoomResourceNotFoundException("Room not found with ID: " + id);
            }

            roomRepository.deleteRoomById(id);
            System.out.println("Room  deleted successfully. ID: " + id);
        } catch (RoomResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
