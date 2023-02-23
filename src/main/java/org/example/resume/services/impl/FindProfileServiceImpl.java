package org.example.resume.services.impl;

import org.example.resume.entities.Profile;
import org.example.resume.repositories.ProfileRepository;
import org.example.resume.services.FindProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProfileServiceImpl implements FindProfileService {

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
}
