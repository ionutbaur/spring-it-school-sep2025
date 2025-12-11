package com.itschool.springapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;

    // 'user_id' will be the column name in the 'orders' table, the foreign key referencing the 'users' table
    // (meaning referencing its primary key - the 'id' column in the 'users' table)
    @ManyToOne
    private User user;

    protected Order() {
        // hibernate needed
    }

    // helper constructors needed for business logic in the service layer
    public Order(String description) {
        this.description = description;
    }

    public Order(String description, User user) {
        this(description);
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
