package org.example.resume.controllers;

import org.example.resume.entities.SkillCategory;
import org.example.resume.forms.SkillForm;
import org.example.resume.security.CurrentProfile;
import org.example.resume.security.SecurityUtils;
import org.example.resume.services.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EditProfileController {

    @Autowired
    private EditProfileService editProfileService;

    @RequestMapping(value = "/my-profile")
    public String getMyProfile(@AuthenticationPrincipal CurrentProfile currentProfile) {
        return String.format("redirect:/%s", currentProfile.getUsername());
    }

    @RequestMapping(value = "/edit")
    public String getEdit() {
        return "edit";
    }

    @GetMapping(value = "/edit/skills")
    public String getEditSkills(Model model) {
        model.addAttribute("skillForm", new SkillForm(editProfileService.getProfileSkills(SecurityUtils.getIdCurrentProfile())));
        model.addAttribute("skillCategories", editProfileService.getAllSkillCategories());
        return "edit/skills";
    }

    @PostMapping(value = "/edit/skills")
    public String saveEditSkills(
            @Valid
            @ModelAttribute("skillForm") SkillForm skillForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<SkillCategory> skillCategories = editProfileService.getAllSkillCategories();
            model.addAttribute("skillCategories", skillCategories);
            return "edit/skills";
        }
        editProfileService.updateProfileSkills(SecurityUtils.getIdCurrentProfile(), skillForm.getItems());
        return "redirect:/aly-dutta";
    }
}
