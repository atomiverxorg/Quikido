package com.quikido.auth.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.quikido.auth.entity.Driver;
import com.quikido.auth.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    public void notifyPassenger(String passengerEmail, String s) {
    }

    public void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder().setTitle(title).setBody(body).build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Notification sent: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
