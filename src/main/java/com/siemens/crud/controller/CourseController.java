package com.siemens.crud.controller;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addJson(@RequestBody CourseDTO courseDTO, Principal principal) {
        return handleAddition(courseDTO, principal.getName());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addForm(CourseDTO courseDTO, Principal principal) {
        return handleAddition(courseDTO, principal.getName());
    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateJson(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return handleUpdate(id, courseDTO);
    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> updateForm(@PathVariable Long id, CourseDTO courseDTO) {
        return handleUpdate(id, courseDTO);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully!");
    }

    private ResponseEntity<String> handleAddition(CourseDTO courseDTO, String username) {
        if (courseService.alreadyExists(courseDTO.getName())) {
            return ResponseEntity.badRequest().body("Course already exists");
        }
        courseService.addCourse(courseDTO, username);
        return ResponseEntity.ok(courseDTO.getName() + " added successfully!");
    }

    private ResponseEntity<String> handleUpdate(Long id, CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok(courseDTO.getName() + " updated successfully!");
    }


}
