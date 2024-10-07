package com.siemens.crud.service.enrollment;

import com.siemens.crud.dto.CourseDTO;

import java.util.List;

public interface EnrollmentService {

    List<CourseDTO> getEnrolledCourses(Long studentId);

    List<CourseDTO> getAvailableCourses(Long studentId);

    CourseDTO enrollCourse(Long courseId, Long studentId);

    boolean alreadyEnrolled(Long studentId, Long courseId);
}
