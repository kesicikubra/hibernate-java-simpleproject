package com.tpe.hotelManagementSystem.controller;
import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import com.tpe.hotelManagementSystem.repository.*;
import com.tpe.hotelManagementSystem.service.*;

import java.util.Scanner;

public class HotelManagementSystem {
    //Step 11 create Scanner
    private static Scanner scanner;

    //Step 12 create displayMenuHotelManagementSystem()
    public static void displayMenuHotelManagementSystem(){
        scanner=new Scanner(System.in);

        //Step 25 create hotelRepository,hotelService instance
        HotelRepository hotelRepository=new HotelRepositoryImpl();
        HotelService hotelService=new HotelServiceImpl(hotelRepository);

        //Step 30a create roomRepository, roomService instance
        RoomRepository roomRepository=new RoomRepositoryImpl();
        RoomService roomService=new RoomServiceImpl(roomRepository,hotelRepository);

        //Step 36a create guestRepository,guestService instance
        GuestRepository guestRepository=new GuestRepositoryImpl();
        GuestService guestService=new GuestServiceImpl(guestRepository);

        //Step 51d create instance of reservationRepository
        ReservationRepository reservationRepository=new ReservationRepositoryImpl();
        ReservationService reservationService=new ReservationServiceImpl(reservationRepository,guestRepository,roomRepository);


        boolean exit=false;
        while(!exit){
            System.out.println("==== Hotel Management System  Menu ====");
            System.out.println("1. Hotel Operations");
            System.out.println("2. Room Operations");
            System.out.println("3. Guest Operations");
            System.out.println("4. Reservation Operations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice= scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    displayHotelOperationsMenu(hotelService);

                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guestService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 5:
                    exit=true;
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("Invalid choice. Please try gain");
                    break;

            }

        }


    }
    //Step 14a create displayHotelOperationsMenu() method
    private static void displayHotelOperationsMenu(HotelService hotelService) {
        System.out.println("HotelOperationMenu"); //Step 14
        scanner = new Scanner(System.in); //Step 15

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    //saveHotel
                    System.out.println("==== Add a new hotel ====");
                    hotelService.saveHotel(); //Step 25a

                    break;
                case 2:
                    //findHotelById
                    System.out.println("Enter the hotel ID: ");
                    Long hotelId = scanner.nextLong();
                    hotelService.findHotelById(hotelId); //Step 26c
                    break;
                case 3:
                    //deleteHotelById
                    System.out.print("Enter the hotel ID to delete: ");
                    Long id = scanner.nextLong();
                    hotelService.deleteHotelById(id); //step 32d
                    break;
                case 4:
                    //findAllHotels
                    System.out.println("==== Find All Hotels ====");
                    hotelService.findAllHotels();  //Step37g
                    break;
                case 5:
                    //updateHotelById
                    System.out.println("==== Update Hotel By ID ====");
                    System.out.print("Enter the hotel ID to update: ");
                    Long hotelId1 = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character

                    //Step 38g complete update method and call hotelService.updateHotel
                    try{
                        System.out.println("Enter the updated hotel name : ");
                        String name=scanner.nextLine();
                        System.out.println("Enter the updated hotel location : ");
                        String location=scanner.nextLine();
                        Hotel updatedHotel=new Hotel();
                        updatedHotel.setId(hotelId1);
                        updatedHotel.setName(name);
                        updatedHotel.setLocation(location);
                        hotelService.updateHotel(hotelId1,updatedHotel);
                    }catch (HotelResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    //Step 14b create displayRoomOperationsMenu() method
    private static void displayRoomOperationsMenu(RoomService roomService) {
        System.out.println("RoomOperationMenu"); //Step 14

        scanner = new Scanner(System.in);  //Step 15
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    //saveRoom
                    System.out.println("==== Add New Room ====");
                    roomService.saveRoom(); //Step 30b

                    break;
                case 2:
                    //findRoomById
                    System.out.print("Enter the Room ID to Find: ");
                    Long roomId = scanner.nextLong();
                    roomService.findRoomById(roomId);  //Step 39g
                    break;
                case 3:
                    //deleteRoomById
                    System.out.println("==== Delete Room By ID ====");
                    System.out.print("Enter the room ID to delete: ");
                    Long roomIdToDelete = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    roomService.deleteRoomById(roomIdToDelete);  //Step 41g
                    break;
                case 4:
                    //findAllRoom
                    System.out.println("==== Find All Rooms ====");
                    roomService.findAllRooms();  //step 40g
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //Step 14c create displayGuestOperationsMenu() method
    private static void displayGuestOperationsMenu(GuestService guestService) {
        System.out.println("GuestOperationMenu"); //Step 14

        scanner = new Scanner(System.in);  //Step 15
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    //saveGuest
                    System.out.println("==== Add New Guest ====");
                    guestService.saveGuest(); //Step 36b
                    break;
                case 2:
                    //findGuestById
                    System.out.print("Enter the Guest ID to Find: ");
                    Long guestId = scanner.nextLong();
                    guestService.findGustById(guestId); //Step 50h
                    break;
                case 3:

                    //DeleteGuestById
                    System.out.println("==== Delete Guest By ID ====");
                    System.out.print("Enter the Guest ID to delete: ");
                    Long guestIdToDelete = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    guestService.deleteGuestId(guestIdToDelete); //Step 52g
                    break;
                case 4:
                    //FindAllGuest
                    System.out.println("==== Find All Guests ====");
                    guestService.findAllGuest(); //Step 53g
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    //Step 14d create displayReservationOperationsMenu() method

    private static void displayReservationOperationsMenu(ReservationService reservationService) {
        System.out.println("ReservationOperationMenu"); //Step 14

        scanner = new Scanner(System.in);  //Step 15

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("==== Add a new reservation ====");
                    //saveReservation
                    reservationService.saveReservation(); //Step 51e
                    break;
                case 2:
                    //step 27e ::findReservationById
                    System.out.print("Enter the reservation ID: ");
                    Long reservationId = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    reservationService.findReservationById(reservationId);//Step 54g
                    break;
                case 3:
                    //step 28e ::findAllReservations
                    System.out.println("==== Find All Reservations ====");
                    reservationService.findAllReservations(); //Step 55g
                    break;
                case 4:
                    //deleteReservationById
                    System.out.print("Enter the reservation ID to delete: ");
                    Long reservationIdToDelete = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    reservationService.deleteReservationById(reservationIdToDelete);//Step 56g
                    break;

                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }

}


