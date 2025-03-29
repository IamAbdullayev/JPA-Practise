package com.abdullayev.apps.others.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//@Entity
//@Table(name = "houses_in_baku")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String houseName;

    @Column(name = "rooms", nullable = false)
    private Integer numberOfRooms;

    @Column(name = "area", nullable = false)
    private Double houseArea;

    @Transient
    private String data;

    @Transient
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public House() {}

    public House(String houseName, Integer numberOfRooms, Double houseArea) {
        this.houseName = houseName;
        this.numberOfRooms = numberOfRooms;
        this.houseArea = houseArea;
        this.data = LocalDateTime.now().format(formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", houseName='" + houseName + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", houseArea=" + houseArea +
                ", data='" + data + '\'' +
                '}';
    }
}
