package com.itschool.springapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // generate a UUID for us (UUID is of type String in Java and VARCHAR in the database)
    private String id;
    private String city;
    private String street;
    private Integer number;
    private Long zipCode;

    /* 'mappedBy' is the field name in the User class that owns the relationship
    (foreign key in "users" table (User entity) referencing "addresses" table (Address entity) ).
    "address" field in User class is mapping a User to an Address.
    */
    @OneToOne(mappedBy = "address")
    private User user;

    protected Address() {
        // hibernate needed
    }

    // helper constructor used for business logic in the service layer
    public Address(String city, String street, Integer number, Long zipCode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String state) {
        this.street = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zip) {
        this.zipCode = zip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
