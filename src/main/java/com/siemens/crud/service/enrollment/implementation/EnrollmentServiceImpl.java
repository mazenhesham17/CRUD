package com.siemens.crud.service.enrollment.implementation;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.mapper.CourseMapper;
import com.siemens.crud.model.Course;
import com.siemens.crud.model.Student;
import com.siemens.crud.repository.CourseRepository;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.enrollment.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseDTO> getEnrolledCourses(Long studentId) {
        return courseMapper.toDTOs(courseRepository.findCoursesEnrolledByStudent(studentId));
    }

    @Override
    public List<CourseDTO> getAvailableCourses(Long studentId) {
        return courseMapper.toDTOs(courseRepository.findCoursesNotEnrolledByStudent(studentId));
    }

    @Override
    public CourseDTO enrollCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        Student student = webUserRepository.findStudentById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);

        webUserRepository.save(student);
        courseRepository.save(course);

        return courseMapper.toDTO(course);
    }

    @Override
    public boolean alreadyEnrolled(Long studentId, Long courseId) {
        return courseRepository.isStudentEnrolledInCourse(studentId, courseId);
    }
}
