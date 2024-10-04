package com.siemens.crud.viewController;

import com.siemens.crud.service.fetch.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/student")
public class StudentViewController {

    final String prefix = "/student";

    @Autowired
    private FetchService fetchService;

    @GetMapping
    public String student() {
        return prefix + "/index";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("student", fetchService.fetchUser(principal.getName()));
        return prefix + "/profile/index";
    }

    @GetMapping("/profile/update")
    public String updateProfile(Model model, Principal principal) {
        model.addAttribute("student", fetchService.fetchUser(principal.getName()));
        return prefix + "/profile/update";
    }
}
