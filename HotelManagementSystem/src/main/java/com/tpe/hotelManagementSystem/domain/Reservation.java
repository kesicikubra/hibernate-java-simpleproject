package com.tpe.hotelManagementSystem.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="t_reservation")
public class Reservation {

    //Step 42d add fields and annotations
    @GeneratedValue(generator = "sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="sequence", //Yukarıdaki isimle aynı olmak zorunda
                       sequenceName = "reservation_id",
                        initialValue = 1000,//başlayacak id no
                        allocationSize = 1) //kaçar kaçar id üretilecek
    @Id
    private Long id;

    @Column(nullable = false)
    private LocalDate checkinDate;
    @Column(nullable = false)
    private LocalDate checkOut;

    //Step 42e Add ManyToOne relationship between Reservation and Guest
    @ManyToOne
    @JoinColumn(name="guest_id")
    private Guest guest;

    //Step 42f Add ManyToOne relationship between Reservation and Guest
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    //Step 42g getter and setter
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    //Step 42h add toString
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkinDate=" + checkinDate +
                ", checkOut=" + checkOut +
//                ", guest=" + guest +
//                ", room=" + room +
                '}';
    }
}
