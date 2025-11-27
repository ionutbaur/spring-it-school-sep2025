package com.itschool.springapp.entity;

import jakarta.persistence.*;

@Entity // Annotation to tell JPA/Hibernate that this class is an entity and should be persisted in the database
@Table(name = "users") // table name in the database. If we don't specify it, the table name will be the same as the Entity name. If not specified, the Entity name defaults to class name
public class User {

    @Id // Annotation to tell Hibernate that this field is the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hibernate will generate new ids for us. GenerationType.IDENTITY will use the database's auto-increment feature (will increment the id by 1)
    private long id;
    private String name;
    private String email;
    private Integer age;

    protected User() {
        // Hibernate needs a non-arg constructor (at least protected), otherwise it will fail
    }

    // helper constructor used for business logic in the service layer
    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getters and setters for all fields needed for Hibernate to work properly

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
