package com.siemens.crud.viewController.teacher;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.service.course.CourseService;
import com.siemens.crud.service.fetch.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherViewController {

    final String prefix = "/teacher";

    @Autowired
    private FetchService fetchService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String teacher() {
        return prefix + "/index";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("teacher", fetchService.fetchUser(principal.getName()));
        return prefix + "/profile/index";
    }

    @GetMapping("/profile/update")
    public String updateProfile(Model model, Principal principal) {
        model.addAttribute("teacher", fetchService.fetchUser(principal.getName()));
        return prefix + "/profile/update";
    }

    @GetMapping("/course")
    public String course(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return prefix + "/course/index";
    }

    @GetMapping("/course/add")
    public String addCourse(Model model, Principal principal) {
        model.addAttribute("teacher", fetchService.fetchUser(principal.getName()));
        return prefix + "/course/add";
    }

    @GetMapping("/course/update/{id}")
    public String updateCourse(@PathVariable Long id, Model model) {
        CourseDTO courseDTO = courseService.getCourseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));
        model.addAttribute("course", courseDTO);
        return prefix + "/course/update";
    }

}
