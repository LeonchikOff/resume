package org.example.resume.services;

import org.example.resume.entities.Profile;
import org.example.resume.models.NotificationMessage;

public interface NotificationSenderService {
    void sendNotification(NotificationMessage notificationMessage);
    String getDestinationAddress(Profile profile);

}
