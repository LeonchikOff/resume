package org.example.resume.services.impl;

import org.example.resume.entities.Profile;
import org.example.resume.models.NotificationMessage;
import org.example.resume.services.NotificationSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.concurrent.ExecutorService;

@Service
public class AsyncEmailNotificationSenderServiceImpl implements NotificationSenderService{
    private final Logger LOGGER = LoggerFactory.getLogger(AsyncEmailNotificationSenderServiceImpl.class);

    @Autowired
    private ExecutorService executorService;

    @Autowired
    JavaMailSender javaMailSender;

    @Value(value = "${email.fromEmail}")
    private String fromEmail;
    @Value(value = "${email.fromName}")
    private String fromName;
    @Value(value = "${email.countOfSendAttempts}")
    private int countOfSendAttempts;

    @Override
    public void sendNotification(NotificationMessage notificationMessage) {
        executorService.submit(new EmailItem(notificationMessage, countOfSendAttempts));
    }

    @Override
    public String getDestinationAddress(Profile profile) {
        return null;
    }

    private class EmailItem implements Runnable {
        private final NotificationMessage notificationMessage;
        int countOfSendAttempts;

        public EmailItem(NotificationMessage notificationMessage, int countOfSendAttempts) {
            this.notificationMessage = notificationMessage;
            this.countOfSendAttempts = countOfSendAttempts;
        }

        @Override
        public void run() {
            try {
                LOGGER.debug("Sending new email to {}", notificationMessage.getDestinationAddress());
                MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), false);
                messageHelper.setSubject(notificationMessage.getSubject());
                messageHelper.setTo(new InternetAddress(notificationMessage.getDestinationAddress(), notificationMessage.getDestinationName()));
                messageHelper.setFrom(fromEmail, fromName);
                messageHelper.setText(notificationMessage.getContent());
                MimeMailMessage message = new MimeMailMessage(messageHelper);
                javaMailSender.send(message.getMimeMessage());
                LOGGER.debug("Email to {} successful sent", notificationMessage.getDestinationAddress());
            } catch (Exception e) {
                LOGGER.error("Can't send email to " + notificationMessage.getDestinationAddress() + ": " + e.getMessage(), e);
                countOfSendAttempts--;
                if (countOfSendAttempts > 0) {
                    LOGGER.debug("Decrement countOfSendAttempts and try again to send email: countOfSendAttempts={} DestinationEmailAddress={}",
                            countOfSendAttempts, notificationMessage.getDestinationAddress());
                    executorService.submit(this);
                } else {
                    LOGGER.error("Email not sent to " + notificationMessage.getDestinationAddress());
                }
            }
        }
    }
}
