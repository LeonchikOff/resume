package org.example.resume.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter

public class NotificationMessage {
    private String destinationAddress;
    private String destinationName;
    private String subject;
    private String content;

    public NotificationMessage(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
