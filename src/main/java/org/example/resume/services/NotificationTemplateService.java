package org.example.resume.services;

import org.example.resume.models.NotificationMessage;

public interface NotificationTemplateService {
    NotificationMessage createNotificationMessage(String templateName, Object model);
}
