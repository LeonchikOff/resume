package org.example.resume.services;

import org.example.resume.entities.Profile;

public interface NotificationManagerService {
    void sendRestoreAccessLink(Profile profile, String restoreLink);
    void sendPasswordChanged(Profile profile);
}
