package com.siemens.crud.viewController;

import com.siemens.crud.dto.FilterDTO;
import com.siemens.crud.service.filter.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/teacher/show")
public class FilterViewController {

    final String prefix = "/teacher/show";

    @Autowired
    FilterService filterService;

    @GetMapping("/student")
    public String showStudents(Model model) {
        model.addAttribute("users", filterService.getByRole("student"));
        return prefix + "/student";
    }

    @GetMapping("/teacher")
    public String showTeachers(Model model) {
        model.addAttribute("users", filterService.getByRole("teacher"));
        return prefix + "/teacher";
    }

    @GetMapping("/filter")
    public String filterUsers(@RequestParam String filterType,
                              @RequestParam String searchTerm,
                              @RequestParam String role,
                              Model model) {
        List<FilterDTO> filteredUsers;
        if ("name".equals(filterType)) {
            filteredUsers = filterService.getByRoleAndName(role, searchTerm);
        } else {
            filteredUsers = filterService.getByRoleAndEmail(role, searchTerm);
        }
        model.addAttribute("users", filteredUsers);
        return prefix + "/" + role.toLowerCase();
    }
}
