package com.siemens.crud.service.course;

import com.siemens.crud.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Optional<CourseDTO> getCourseById(Long id);

    List<CourseDTO> getAllCourses();

    CourseDTO addCourse(CourseDTO courseDTO, String lecturerUsername);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    void deleteCourse(Long id);

    boolean alreadyExists(String courseName);
}
