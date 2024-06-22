package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Guest;
import com.tpe.hotelManagementSystem.domain.Reservation;
import com.tpe.hotelManagementSystem.domain.Room;
import com.tpe.hotelManagementSystem.exception.ReservationNotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    //Step 49c write saveResevation codes
    @Override
    public Reservation saveReservation(Reservation reservation) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();



            session.persist(reservation);

            transaction.commit();
            session.close();
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Step 54c write findReservationbyId codes
    @Override
    public Reservation findReservationById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Reservation reservation = session.get(Reservation.class, id);
            Hibernate.initialize(reservation); // Explicitly initialize the proxy object
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservationNotFoundException("Issue occurred during fetching Reservation");
        }
    }

    //Step 55c write findAllReservations codes
    @Override
    public List<Reservation> findAllReservations() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "FROM Reservation";
            List<Reservation> reservations = session.createQuery(hql, Reservation.class).getResultList();
            if (reservations.isEmpty()) {
                System.out.println("Reservation is empty");
            }
            //System.out.println(reservations);
            return reservations;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservationNotFoundException("Failed to fetch reservations.");
        }
    }

    //Step 56c write deleteReservationById codes
    @Override
    public void deleteReservationById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Reservation reservation = session.get(Reservation.class, id);
            if (reservation != null) {

                session.delete(reservation);
                transaction.commit();
                session.close();
            } else {
                System.out.println("Reservation not found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
