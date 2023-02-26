package org.example.resume.services.impl;

import org.example.resume.entities.Profile;
import org.example.resume.repositories.ProfileRepository;
import org.example.resume.security.CurrentProfile;
import org.example.resume.services.FindProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindProfileServiceImpl implements FindProfileService, UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(FindProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Profile findProfileByUid(String uid) {
        return profileRepository.findByUid(uid);
    }

    @Override
    public Page<Profile> findPageableListProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUid(username);
        if(profile == null) {
            profile = profileRepository.findByEmail(username);
            if(profile == null)
                profile = profileRepository.findByPhone(username);
        }
        if(profile != null) {
            return new CurrentProfile(profile);
        } else {
            LOGGER.error("Profile not found by " + username);
            throw new UsernameNotFoundException("Profile not found by " + username);
        }
    }
}
