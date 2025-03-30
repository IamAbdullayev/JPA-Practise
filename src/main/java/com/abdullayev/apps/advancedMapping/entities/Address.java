package com.abdullayev.apps.advancedMapping.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name = "street")
    private String street;
    @Column(name = "avenue")
    private String avenue;

    public Address() {
    }

    public Address(String street, String avenue) {
        this.street = street;
        this.avenue = avenue;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", avenue='" + avenue + '\'' +
                '}';
    }
}