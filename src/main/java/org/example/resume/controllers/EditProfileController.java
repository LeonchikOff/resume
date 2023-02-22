package org.example.resume.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditProfileController {

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(){
        return "edit";
    }
}
