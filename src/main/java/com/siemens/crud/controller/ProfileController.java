package com.siemens.crud.controller;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/update")
    public String updateProfile(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam(required = false) String password,
                                Principal principal) {
        WebUserDTO webUserDTO = new WebUserDTO();

        webUserDTO.setEmail(principal.getName());

        webUserDTO.setFirstName(firstName);
        webUserDTO.setLastName(lastName);
        webUserDTO.setPassword(password);

        profileService.updateProfile(webUserDTO);

        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String deleteProfile(Principal principal) {
        profileService.deleteProfile(principal.getName());
        return "redirect:/logout";
    }

}
