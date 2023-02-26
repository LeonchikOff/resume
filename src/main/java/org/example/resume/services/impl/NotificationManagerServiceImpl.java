package org.example.resume.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.resume.entities.Profile;
import org.example.resume.models.NotificationMessage;
import org.example.resume.services.NotificationManagerService;
import org.example.resume.services.NotificationSenderService;
import org.example.resume.services.NotificationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NotificationManagerServiceImpl implements NotificationManagerService {
    private final Logger LOGGER = LoggerFactory.getLogger(NotificationManagerServiceImpl.class);

    @Autowired
    private NotificationTemplateService notificationTemplateService;

    @Autowired
    private NotificationSenderService notificationSenderService;

    @Override
    public void sendRestoreAccessLink(Profile profile, String restoreLink) {
        LOGGER.debug("Restore link: {} for profile {}", restoreLink, profile.getUid());
        HashMap<String, Object> modelMapForTemplating = new HashMap<>();
        modelMapForTemplating.put("restoreLink", restoreLink);
        modelMapForTemplating.put("profile", profile);
        processNotification(profile, modelMapForTemplating, "restoreAccessNotification");
    }

    @Override
    public void sendPasswordChanged(Profile profile) {
        HashMap<String, Object> modelMapForTemplating = new HashMap<>();
        modelMapForTemplating.put("profile", profile);
        processNotification(profile, modelMapForTemplating, "passwordChangedNotification");
    }

    private void processNotification(Profile profile, HashMap<String, Object> modelMapForTemplating, String templateName) {
        String destinationAddress = notificationSenderService.getDestinationAddress(profile);
        if (StringUtils.isNotBlank(destinationAddress)) {
            NotificationMessage restoreAccessNotificationMessage =
                    notificationTemplateService.createNotificationMessage(templateName, modelMapForTemplating);
            restoreAccessNotificationMessage.setDestinationAddress(destinationAddress);
            restoreAccessNotificationMessage.setDestinationName(profile.getFirstName());
            notificationSenderService.sendNotification(restoreAccessNotificationMessage);
        } else
            LOGGER.error("Notification ignored: destinationAddress is empty for profile {}", profile.getUid());
    }
}
