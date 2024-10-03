package com.siemens.crud.viewController;

import com.siemens.crud.mapper.WebUserMapper;
import com.siemens.crud.model.WebUser;
import com.siemens.crud.repository.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherViewController {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private WebUserMapper webUserMapper;

    @GetMapping
    public String teacher(Model model, Principal principal) {
        final String email = principal.getName();
        WebUser user = webUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("teacher", webUserMapper.toDTO(user));
        return "teacher";
    }

}
