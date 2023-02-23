package org.example.resume.services;

import org.example.resume.repositories.ProfileRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RemoveNotCompletedProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Value(value = "${remove.not.completed.profiles.interval.in.days}")
    private int removeNotCompletedProfilesIntervalInDays;

    @Transactional
    @Scheduled(cron = "0 59 23 * * *")
    public void removeNotCompletedProfiles() {
        DateTime criticalDateTime = DateTime.now().minusDays(removeNotCompletedProfilesIntervalInDays);
        profileRepository
                .findByCompletedFalseAndCreatedBefore(new Timestamp(criticalDateTime.getMillis()))
                .forEach(profile -> profileRepository.delete(profile));
    }
}
