package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Address;
import com.tpe.hotelManagementSystem.domain.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GuestRepositoryImpl implements GuestRepository {

    //Step 35 c write saveGuest methods codes
    @Override
    public void saveGuest(Guest guest) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

//            Address address = new Address();
//            address.setStreet(guest.getAddress().getStreet());
//            address.setCity(guest.getAddress().getCity());
//            address.setCountry(guest.getAddress().getCountry());
//            address.setZipCode(guest.getAddress().getZipCode());
//
//            guest.setAddress(address);
            session.persist(guest);
            transaction.commit();
            HibernateUtils.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //Step 50c write findGuestById codes
//    @Override
//    public Guest findGuestById(Long id) {
//        Session session=HibernateUtils.getSessionFactory().openSession();
//        return session.get(Guest.class,id);
//    }

    @Override
    public Guest findGuestById(Long guestId) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder(); //criteriabuilder oluşturuldu, methodları kullanılabilir
        CriteriaQuery<Guest> query = builder.createQuery(Guest.class); //Sonuçlar guest data type inde olacak
        Root<Guest> root = query.from(Guest.class); //root isminde kök oluşturuldu
        query.select(root).where(builder.equal(root.get("id"), guestId)); //sorgu seçimi ve filtreleme
        Guest guest = session.createQuery(query).uniqueResult();
        return guest;
    }

    //Step 52c write deleteguestbyıd codes
    @Override
    public void deleteGuestById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Guest guest = session.get(Guest.class, id);
            if (guest != null) {
                // Remove the guest from any associated reservations
                guest.getReservations().forEach(reservation -> reservation.setGuest(null));

                // Delete the guest
                session.delete(guest);
            }

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Step 53c write findAllGuest codes
    @Override
    public List<Guest> findAllGuest() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        String hql = "FROM Guest";
        List<Guest> guests = session.createQuery(hql, Guest.class).getResultList();
        return guests;
    }
}
