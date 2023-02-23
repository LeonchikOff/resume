package org.example.resume.services.impl;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.text.WordUtils;
import org.example.resume.entities.Profile;
import org.example.resume.entities.Skill;
import org.example.resume.entities.SkillCategory;
import org.example.resume.exception.CantCompleteClientRequestException;
import org.example.resume.forms.SignUpForm;
import org.example.resume.repositories.ProfileRepository;
import org.example.resume.repositories.SkillCategoryRepository;
import org.example.resume.services.EditProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class EditProfileServiceImpl implements EditProfileService {
    private final Logger LOGGER = LoggerFactory.getLogger(EditProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Value(value = "${generate.uid.alphabet}")
    private String generateUidAlphabet;
    @Value(value = "${generate.uid.suffix.length}")
    private int generateUidSuffixLength;
    @Value(value = "${generate.uid.max.try.count}")
    private int maxTryCountToGenerateUid;


    @Override
    @Transactional
    public Profile createNewProfile(SignUpForm signUpForm) {
        String baseUid = (signUpForm.getFirstName().trim() + "-" + signUpForm.getLastName().trim()).toLowerCase();
        String uid = baseUid;
        if (profileRepository.countByUid(baseUid) > 0) {
            Random random = new Random();
            for (int i = 0; profileRepository.countByUid(uid) > 0; i++) {
                if (i < maxTryCountToGenerateUid) {
                    StringBuilder stringBuilderUid = new StringBuilder();
                    for (int j = 0; j < generateUidSuffixLength; j++) {
                        stringBuilderUid
                                .append(generateUidAlphabet.charAt(random.nextInt(generateUidAlphabet.length())));
                    }
                    uid = baseUid + "-" + stringBuilderUid.toString();
                } else throw new CantCompleteClientRequestException
                        ("Can't generate unique uid for profile: " + baseUid + ": maxTryCountToGenerateUid detected");
            }
        }
        Profile profile = new Profile();
        profile.setUid(uid);
        profile.setFirstName(WordUtils.capitalize(signUpForm.getFirstName().trim().toLowerCase()));
        profile.setLastName(WordUtils.capitalize(signUpForm.getLastName().trim().toLowerCase()));
        profile.setPassword(signUpForm.getPassword());
        profile.setCompleted(false);
        profileRepository.save(profile);
        return profile;
    }

    @Override
    @Transactional
    public void updateProfileSkills(Long idProfile, List<Skill> updatableSkills) {
        profileRepository.findById(idProfile).ifPresent(profile -> {
            if (!CollectionUtils.isEqualCollection(updatableSkills, profile.getSkills())) {
                profile.setSkills(updatableSkills);
                profileRepository.save(profile);
            } else {
                LOGGER.debug("Profile skills: nothing to update");
            }
        });
    }

    @Override
    public List<Skill> getProfileSkills(Long idProfile) {
        return profileRepository.findById(idProfile).map(Profile::getSkills).orElse(Collections.emptyList());
    }

    @Override
    public List<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAll(Sort.by("id"));
    }
}
