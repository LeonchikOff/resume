package org.example.resume.services.impl;

import org.example.resume.components.NotificationContentResolver;
import org.example.resume.exception.CantCompleteClientRequestException;
import org.example.resume.models.NotificationMessage;
import org.example.resume.services.NotificationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final Logger LOGGER = LoggerFactory.getLogger(NotificationTemplateServiceImpl.class);

    @Value(value = "${notification.config.path}")
    private String notificationConfigPath;

    @Autowired
    private NotificationContentResolver notificationContentResolver;

    private Map<String, NotificationMessage> notificationMessageMapTemplates;

    @Override
    public NotificationMessage createNotificationMessage(String templateBeanName, Object model) {
        NotificationMessage notificationMessageTemplate = notificationMessageMapTemplates.get(templateBeanName);
        if(notificationMessageTemplate == null)
            throw new CantCompleteClientRequestException("Notification template " + templateBeanName + "not found");
        String resolvedSubject = notificationContentResolver.resolve(notificationMessageTemplate.getSubject(), model);
        String resolvedContent = notificationContentResolver.resolve(notificationMessageTemplate.getContent(), model);
        return new NotificationMessage(resolvedSubject, resolvedContent);
    }

    @PostConstruct
    private void postConstruct() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.setValidating(false);
        xmlBeanDefinitionReader.loadBeanDefinitions(new PathResource(notificationConfigPath));
        Map<String, NotificationMessage> notificationTemplatesBean
                = defaultListableBeanFactory.getBeansOfType(NotificationMessage.class);
        notificationMessageMapTemplates = Collections.unmodifiableMap(notificationTemplatesBean);
        LOGGER.info("Loaded {} notification templates", notificationMessageMapTemplates.size());
    }
}
