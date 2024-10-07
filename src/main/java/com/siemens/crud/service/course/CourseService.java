package com.siemens.crud.service.course;

import com.siemens.crud.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    CourseDTO getCourseById(Long courseId);

    List<CourseDTO> getAllCourses();

    CourseDTO addCourse(CourseDTO courseDTO, Long userId);

    CourseDTO updateCourse(Long courseId, CourseDTO courseDTO);

    void deleteCourse(Long courseId);

    boolean alreadyExists(String courseName);
}
