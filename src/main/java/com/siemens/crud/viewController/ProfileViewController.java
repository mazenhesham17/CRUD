package com.siemens.crud.viewController;

import com.siemens.crud.security.CustomUserDetails;
import com.siemens.crud.service.fetch.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileViewController {

    @Autowired
    private FetchService fetchService;

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", fetchService.fetchUser(userDetails.getId()));
        return "/profile/index";
    }

    @GetMapping("/profile/update")
    public String updateProfile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", fetchService.fetchUser(userDetails.getId()));
        return "/profile/update";
    }
}
