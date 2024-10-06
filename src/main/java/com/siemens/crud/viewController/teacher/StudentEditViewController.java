package com.siemens.crud.viewController.teacher;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.service.fetch.FetchService;
import com.siemens.crud.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class StudentEditViewController {

    final String prefix = "/teacher/show";

    @Autowired
    private ProfileService profileService;

    @Autowired
    private FetchService fetchService;

    @GetMapping("/update/{id}")
    public String updateProfilePage(@PathVariable Long id, Model model) {
        model.addAttribute("student", fetchService.fetchUser(id));
        return prefix + "/update";
    }


    @PostMapping("/update/{id}")
    public String updateProfile(@PathVariable Long id,
                                @RequestParam String firstName,
                                @RequestParam String lastName) {
        WebUserDTO webUserDTO = new WebUserDTO();

        webUserDTO.setId(id);

        webUserDTO.setFirstName(firstName);
        webUserDTO.setLastName(lastName);

        webUserDTO = profileService.updateProfileById(webUserDTO);

        return "redirect:" + prefix + "/student";
    }

    @PostMapping("/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return "redirect:" + prefix + "/student";
    }

    @GetMapping("/student/add")
    public String showAddStudentPage() {
        return prefix + "/add";
    }

}
