package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepositoryImpl implements HotelRepository {

    //Step 22a create saveHotel
    @Override
    public Hotel saveHotel(Hotel hotel) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(hotel);
            transaction.commit();
            HibernateUtils.closeSession(session); //session.close();
            return hotel;
        } catch (Exception e) {
            e.printStackTrace(); //Hatanın nerede hangi methodda tetiklendiğini bildirir
        }

        return null;
    }

    //Step 26b findHotelById
    @Override
    public Hotel findHotelById(Long id) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        return session.get(Hotel.class, id);

    }

    //Step 31c deleteHotelById
    @Override
    public void deleteHotelById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Hotel deleteHotel = session.get(Hotel.class,id);
            if(deleteHotel!=null){
                session.delete(deleteHotel);
                transaction.commit();
                HibernateUtils.closeSession(session);
            }else{
                throw new HotelResourceNotFoundException("Hotel not found with id : "+id);
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }

    }

    //Step 37c write finaAllHotels codes
    @Override
    public List<Hotel> findAllHotels() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Hotel> hotels = session.createQuery("from Hotel", Hotel.class).getResultList();
        return hotels;
    }


    //Step 38c write updateHotel codes
    @Override
    public void updateHotel(Hotel hotel) {

        try(Session session=HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
             Hotel existingHotel=session.get(Hotel.class, hotel.getId());
             if(existingHotel!=null){
                 existingHotel.setName(hotel.getName());
                 existingHotel.setLocation(hotel.getLocation());

                 session.update(existingHotel);
             }
             transaction.commit();
             HibernateUtils.closeSession(session);
        }catch (HibernateException e){
            e.printStackTrace();
        }

    }
}
