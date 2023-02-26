package org.example.resume.components.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.resume.components.NotificationContentResolver;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.StringReader;

@Component
public class NotificationContentResolverImpl implements NotificationContentResolver {
    @Override
    public String resolve(String templateData, Object model) {
        try {
            Template template =
                    new Template("", new StringReader(templateData), new Configuration(Configuration.getVersion()));
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException exception) {
            throw new IllegalArgumentException("Can't resolve string template: " + exception.getMessage(), exception);
        }
    }
}
