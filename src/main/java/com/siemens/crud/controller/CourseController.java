package com.siemens.crud.controller;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.security.CustomUserDetails;
import com.siemens.crud.service.course.CourseService;
import com.siemens.crud.service.enrollment.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addJson(@RequestBody CourseDTO courseDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return handleAddition(courseDTO, userDetails.getId());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addForm(CourseDTO courseDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return handleAddition(courseDTO, userDetails.getId());
    }

    @PostMapping(value = "/update/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateJson(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO) {
        return handleUpdate(courseId, courseDTO);
    }

    @PostMapping(value = "/update/{courseId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> updateForm(@PathVariable Long courseId, CourseDTO courseDTO) {
        return handleUpdate(courseId, courseDTO);
    }

    @PostMapping(value = "/delete/{courseId}")
    public ResponseEntity<String> delete(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully!");
    }

    @PostMapping(value = "/enroll/{courseId}")
    public ResponseEntity<String> enroll(@PathVariable Long courseId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        final Long userId = userDetails.getId();
        if (enrollmentService.alreadyEnrolled(courseId, userId)) {
            return ResponseEntity.ok("Student already enrolled!");
        }
        enrollmentService.enrollCourse(courseId, userId);
        return ResponseEntity.ok("Student enrolled!");
    }

    private ResponseEntity<String> handleAddition(CourseDTO courseDTO, Long userId) {
        if (courseService.alreadyExists(courseDTO.getName())) {
            return ResponseEntity.badRequest().body("Course already exists");
        }
        courseDTO = courseService.addCourse(courseDTO, userId);
        return ResponseEntity.ok(courseDTO.getName() + " added successfully!");
    }

    private ResponseEntity<String> handleUpdate(Long courseId, CourseDTO courseDTO) {
        courseDTO = courseService.updateCourse(courseId, courseDTO);
        return ResponseEntity.ok(courseDTO.getName() + " updated successfully!");
    }


}
