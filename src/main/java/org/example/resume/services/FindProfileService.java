package org.example.resume.services;

import org.example.resume.entities.Profile;

public interface FindProfileService {

    Profile findByUid(String uid);
}
