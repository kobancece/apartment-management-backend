package com.apt.tracker.apartmentmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "addressID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressID;

    @Column(name = "userID")
    private Integer userId;

    @Column(name = "streetName")
    private String streetName;

    @Column(name = "cityName")
    private String cityName;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "state")
    private String state;

    public Address() {}

    public Address(Integer userId, String streetName, String cityName, String postalCode, String state) {
        this.userId = userId;
        this.streetName = streetName;
        this.cityName = cityName;
        this.postalCode = postalCode;
        this.state = state;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
