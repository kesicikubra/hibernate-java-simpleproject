package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Address;
import com.tpe.hotelManagementSystem.domain.Guest;
import com.tpe.hotelManagementSystem.exception.GuestNotFoundException;
import com.tpe.hotelManagementSystem.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestServiceImpl implements GuestService {

    //Step 35g scanner
    private static Scanner scanner;

    //Step 35h create guestRepository constructor injection
    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    //Step 35i write saveGuest method codes
    @Override
    public Guest saveGuest() {
        scanner = new Scanner(System.in);
        Guest guest = new Guest();
        //id otomatik generate edildiği için id sorulmadı
        System.out.println("Enter guest name : ");
        guest.setName(scanner.nextLine());

        Address address = new Address();

        System.out.println("Enter guest Street : ");
        address.setStreet(scanner.nextLine());

        System.out.println("Enter guest city : ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter guest country : ");
        address.setCountry(scanner.nextLine());

        System.out.println("Enter guest zipCode : ");
        address.setZipCode(scanner.nextInt());

        guest.setAddress(address);

        guestRepository.saveGuest(guest);
        System.out.println("Guest saved successfully. Guest id : " + guest.getId());
        return guest;
    }


    //Step 50g write findGuestById codes
    @Override
    public void findGustById(Long id) {
        try {
            Guest foundGuest = guestRepository.findGuestById(id);
            if(foundGuest!=null){
                System.out.println("*-----------------------------*");
                System.out.println(foundGuest);
            }else{
                throw new GuestNotFoundException("Guest not found with id : "+id);
            }
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }

    }


    //Step 52f write deleteguestbyıd codes
    @Override
    public void deleteGuestId(Long id) {
        try {
            Guest existingGuest = guestRepository.findGuestById(id);
            if (existingGuest == null) {
                throw new GuestNotFoundException("Guest  not found with ID: " + id);
            }
            guestRepository.deleteGuestById(id);
            throw new GuestNotFoundException("Guest  delete successfully  with ID: " + id);
        } catch (GuestNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    //Step 53f write findAllGuest codes
    @Override
    public List<Guest> findAllGuest() {
        try {
            List<Guest> guests = guestRepository.findAllGuest();
            if (!guests.isEmpty()) {
                for (Guest guest : guests) {
                    System.out.println(guest);
                }
            } else {
                throw new GuestNotFoundException("Guest  not found");
            }
            return guests;
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
