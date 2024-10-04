package com.siemens.crud.service.enrollment;

import com.siemens.crud.dto.CourseDTO;

import java.util.List;

public interface EnrollmentService {

    List<CourseDTO> getEnrolledCourses(String studentEmail);

    List<CourseDTO> getAvailableCourses(String studentEmail);

    CourseDTO enrollCourse(Long courseId, String studentEmail);

    boolean alreadyEnrolled(Long courseId, String studentEmail);
}
