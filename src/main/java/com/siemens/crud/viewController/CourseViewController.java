package com.siemens.crud.viewController;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.model.Course;
import com.siemens.crud.security.CustomUserDetails;
import com.siemens.crud.service.course.CourseService;
import com.siemens.crud.service.enrollment.EnrollmentService;
import com.siemens.crud.service.fetch.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseViewController {

    final String prefix = "/course";

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private FetchService fetchService;

    @GetMapping
    public String courses(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails.getAuthorities().contains("ROLE_TEACHER")) {
            model.addAttribute("courses", courseService.getAllCourses());
        } else {
            model.addAttribute("toEnroll", true);
            model.addAttribute("courses", enrollmentService.getAvailableCourses(userDetails.getId()));
        }
        return prefix + "/index";
    }

    @GetMapping("/enrolled")
    public String enrolledCourses(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("toEnroll", false);
        model.addAttribute("courses", enrollmentService.getEnrolledCourses(userDetails.getId()));
        return prefix + "/index";
    }


    @GetMapping("/add")
    public String addCourse(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("teacher", fetchService.fetchUser(userDetails.getId()));
        return prefix + "/add";
    }

    @GetMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return prefix + "/update";
    }
}
