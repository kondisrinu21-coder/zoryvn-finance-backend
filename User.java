package com.zorvyn.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="users")
public class User {
    @Id @GeneratedValue private Long id;
    private String email, role;
    private boolean active=true;
    private LocalDateTime createdAt=LocalDateTime.now();
    public User(){}
    public User(String email,String role){this.email=email;this.role=role;}
    // Getters/Setters
    public String getEmail(){return email;}
    public String getRole(){return role;}
    public boolean isActive(){return active;}
    public Long getId(){return id;}
}
