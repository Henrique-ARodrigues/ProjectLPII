package com.banking.model;

import java.util.UUID;


public class User {

    private UUID id;
    private String email;
    private String password;

    

    public User(String email, String password) {
        
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        
    }

    

    // Getters
    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password ;
    }

    
}