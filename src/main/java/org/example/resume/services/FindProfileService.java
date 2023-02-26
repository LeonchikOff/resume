package org.example.resume.services;

import org.example.resume.entities.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface FindProfileService {
    Profile findProfileByUid(String uid);
    Page<Profile> findPageableListProfiles(Pageable pageable);
}
