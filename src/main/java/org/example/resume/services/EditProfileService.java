package org.example.resume.services;

import org.example.resume.entities.Profile;
import org.example.resume.entities.Skill;
import org.example.resume.entities.SkillCategory;
import org.example.resume.forms.SignUpForm;

import java.util.List;

public interface EditProfileService {
    Profile createNewProfile(SignUpForm signUpForm);
    void updateProfileSkills(Long idProfile, List<Skill> updatableSkills);
    List<Skill> getProfileSkills(Long idProfile);
    List<SkillCategory> getAllSkillCategories();
}
