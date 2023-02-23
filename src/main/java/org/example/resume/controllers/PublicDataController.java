package org.example.resume.controllers;

import org.example.resume.constants.Constants;
import org.example.resume.entities.Profile;
import org.example.resume.services.FindProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicDataController {

    @Autowired
    private FindProfileService findProfileService;

    @GetMapping(value = "/{uid}")
    public String getProfile(@PathVariable("uid") String uid, Model model) {
        Profile profile = findProfileService.findProfileByUid(uid);
        //TODO if(profile == null)
        model.addAttribute("profile", profile);
        return "profile";
    }

    @RequestMapping(value = "/welcome")
    public String listProfiles(Model model) {
        PageRequest pageRequest = PageRequest.of(0, Constants.MAX_PROFILES_PER_PAGE, Sort.by("id"));
        Page<Profile> pageProfiles = findProfileService.findPageableListProfiles(pageRequest);
        model.addAttribute("page", pageProfiles);
        model.addAttribute("profiles", pageProfiles.getContent());
        return "profiles";
    }

    @GetMapping(value = "/fragment/more")
    public String moreProfiles(@PageableDefault(size = Constants.MAX_PROFILES_PER_PAGE)
                               @SortDefault(sort = "id") Pageable pageable, Model model) {
        Page<Profile> pageProfiles = findProfileService.findPageableListProfiles(pageable);
        model.addAttribute("profiles", pageProfiles.getContent());
        return "fragment/profile-items";
    }

    @GetMapping(value = "/error")
    public String getError() {
        return "error";
    }


}
