package com.quikido.auth.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
	public String getPassword1() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPassword(String hashpw) {
		// TODO Auto-generated method stub
		
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

    // Getters and Setters
}