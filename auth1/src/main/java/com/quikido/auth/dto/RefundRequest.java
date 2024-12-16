package com.quikido.auth.dto;

import com.quikido.auth.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class RefundRequest {
    @Id
    private Long id;

    @ManyToOne
    public User userId;
    public double amount;
    public String reason;

}
