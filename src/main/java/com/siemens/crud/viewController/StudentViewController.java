package com.siemens.crud.viewController;

import com.siemens.crud.service.enrollment.EnrollmentService;
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
    private EnrollmentService enrollmentService;

    @GetMapping
    public String student() {
        return prefix + "/index";
    }

    @GetMapping("/course")
    public String courses(Model model, Principal principal) {
        model.addAttribute("courses", enrollmentService.getAvailableCourses(principal.getName()));
        return prefix + "/course/index";
    }

    @GetMapping("/course/enrolled")
    public String enrolledCourses(Model model, Principal principal) {
        model.addAttribute("courses", enrollmentService.getEnrolledCourses(principal.getName()));
        return prefix + "/course/enrolled";
    }
}
