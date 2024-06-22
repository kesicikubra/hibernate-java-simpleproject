package com.tpe.hotelManagementSystem.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Bu class ta hotel tablosu oluşturulacak
@Entity
@Table(name="t_hotel")
public class Hotel {

    //Step 17a add fields
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    //Step 20a OneToMany ralationship in Hotel.class
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms=new ArrayList<>();

    //Step 20b constructor
    public Hotel() {
    }

    public Hotel(Long id, String name, String location, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rooms = rooms;
    }

    //Step 20c getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    //Step 20d toString
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
