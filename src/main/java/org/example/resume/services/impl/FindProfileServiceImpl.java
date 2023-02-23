package org.example.resume.services.impl;

import org.example.resume.entities.Profile;
import org.example.resume.repositories.ProfileRepository;
import org.example.resume.services.FindProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProfileServiceImpl implements FindProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Profile findByUid(String uid) {
        return profileRepository.findByUid(uid);
    }
}
