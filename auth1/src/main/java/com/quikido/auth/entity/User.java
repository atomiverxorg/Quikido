package com.quikido.auth.entity;

import com.quikido.auth.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, unique = true)
    private String email;

//    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("PASSENGER"); // Default role

    private boolean status = true; // Active by default

    private String referralCode;

    private String DeviceToken;

    @ManyToOne
    private Wallet wallet;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public boolean getStatus() {
        return status;
    }

    public String getReferralCode() {
        return referralCode;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }
}
