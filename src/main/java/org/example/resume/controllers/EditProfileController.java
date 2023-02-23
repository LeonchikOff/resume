package org.example.resume.controllers;

import org.example.resume.entities.SkillCategory;
import org.example.resume.forms.SkillForm;
import org.example.resume.services.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/edit")
public class EditProfileController {

    @Autowired
    private EditProfileService editProfileService;


    @GetMapping
    public String getEdit() {
        return "edit";
    }

    @GetMapping(value = "/skills")
    public String getEditSkills(Model model) {
        model.addAttribute("skillForm", new SkillForm(editProfileService.getProfileSkills(1L)));
        model.addAttribute("skillCategories", editProfileService.getAllSkillCategories());
        return "edit/skills";
    }

    @PostMapping(value = "/skills")
    public String saveEditSkills(
            @Valid
            @ModelAttribute("skillForm") SkillForm skillForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<SkillCategory> skillCategories = editProfileService.getAllSkillCategories();
            model.addAttribute("skillCategories", skillCategories);
            return "edit/skills";
        }
        editProfileService.updateProfileSkills(1L, skillForm.getItems());
        return "redirect:/aly-dutta";
    }


}
