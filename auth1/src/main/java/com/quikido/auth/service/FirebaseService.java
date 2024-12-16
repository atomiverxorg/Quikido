package com.quikido.auth.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.Notification;
import com.quikido.auth.entity.Driver;
import com.quikido.auth.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private static final FirebaseApp firebaseApp = FirebaseApp.initializeApp();

    @Autowired
    private DriverRepository driverRepository;


    public void sendPushNotification(String deviceToken, String title, String message) throws FirebaseMessagingException {
        // Create message
        Message notificationMessage = Message.builder()
                .setToken(deviceToken)
                .setNotification(new Notification(title, message))
                .build();

        // Send notification
        String response = FirebaseMessaging.getInstance(firebaseApp).send(notificationMessage);
        System.out.println("Successfully sent message: " + response);
    }
    public void notifyDriver(Long driverId, String message) throws FirebaseMessagingException {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
        sendPushNotification(driver.getDeviceToken(), "New Ride", message);
    }

}
