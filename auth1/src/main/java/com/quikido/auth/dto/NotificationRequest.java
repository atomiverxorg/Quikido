package com.quikido.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {
    private Long userId;
    private String message;
    private String title;

}
