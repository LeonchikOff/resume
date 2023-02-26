package org.example.resume.security;

import lombok.Getter;
import org.example.resume.constants.Constants;
import org.example.resume.entities.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class CurrentProfile extends User {
    private final Long id;
    private final String fullName;

    public CurrentProfile(Profile profile) {
        super(profile.getUid(), profile.getPassword(), true, true, true, true,
                Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
        this.id = profile.getId();
        this.fullName = profile.getFullName();
    }

    @Override
    public String toString() {
        return "CurrentProfile(id=" + this.getId() + ", username=" + this.getUsername() + ")";
    }


}
