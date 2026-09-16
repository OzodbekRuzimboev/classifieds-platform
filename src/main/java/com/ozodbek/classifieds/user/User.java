package com.ozodbek.classifieds.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true,  length = 255)
    private String email;

    protected  User() {
    }

    public User(String name, String email) {
        changeName(name);
        changeEmail(email);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void changeName(String name) {
        if (name == null ||  name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        this.name = name.trim();
    }
    public void changeEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        this.email = email.trim().toLowerCase();
    }
}

