package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import com.tpe.hotelManagementSystem.repository.HotelRepository;
import com.tpe.hotelManagementSystem.repository.HotelRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class HotelServiceImpl implements HotelService {

    private static Scanner scanner;

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    //HotelRepository hotelRepository=new HotelRepositoryImpl();


    //Step 24a create saveHotel method
    @Override
    public Hotel saveHotel() {
        scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        System.out.print("Enter hotel Id: ");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();//Consume the newline character
        System.out.print("Enter hotel name: ");
        hotel.setName(scanner.nextLine());
        System.out.print("Enter hotel location: ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.saveHotel(hotel);
        System.out.println("Hotel saved successfully. Hotel ID : " + hotel.getId());

        return hotel;
    }

    //Step 26d indHotelById
    @Override
    public Hotel findHotelById(Long id) {
        try {
            Hotel foundHotel = hotelRepository.findHotelById(id);
            if (foundHotel != null) {
                System.out.println("----------------------------------");
                System.out.println(foundHotel);
                return foundHotel;
            } else {
                throw new HotelResourceNotFoundException("Hotel not found with id : " + id);
            }
        } catch (HotelResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //step 32c write deleteHotelById methods codes
    @Override
    public void deleteHotelById(Long id) {
        scanner = new Scanner(System.in);
        Hotel hotelDelete = hotelRepository.findHotelById(id);
        if (hotelDelete == null) {
            throw new HotelResourceNotFoundException("Hotel not found with id : " + id);
        }
        System.out.println(hotelDelete);
        System.out.println("Are you sure you want to delete hotel with id: " + id + " Please answer with Y or N");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("Y")) {
            hotelRepository.deleteHotelById(hotelDelete.getId());
            System.out.println("Hotel is deleted successfully...");
        } else {
            System.out.println("Delete operation cancelled...");
        }
    }

    //Step 37f write findAllHotels codes
    @Override
    public List<Hotel> findAllHotels() {
        try {
            List<Hotel> hotels = hotelRepository.findAllHotels();
            if (!hotels.isEmpty()) {
                System.out.println("List of hotels : ");
                for (Hotel hotel : hotels) {
                    System.out.println(hotel);
                    System.out.println("*------------------------------------------*");
                }
            } else {
                System.out.println("Hotel list is empty.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving hotels " + e.getMessage());
        }

        return null;
    }

    //Step38f write updateHotel codes
    @Override
    public void updateHotel(Long id, Hotel updateHotel) {
        try {
            Hotel existingHotel = hotelRepository.findHotelById(id);
            if(existingHotel==null){
                throw new HotelResourceNotFoundException("Hotel not found...");
            }
            existingHotel.setName(updateHotel.getName());
            existingHotel.setLocation(updateHotel.getLocation());

            hotelRepository.updateHotel(existingHotel);
            System.out.println("Hotel updated successfully...");
        }catch (HotelResourceNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
