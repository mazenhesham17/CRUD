package com.siemens.crud.service.enrollment.implementation;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.dto.StudentDTO;
import com.siemens.crud.mapper.CourseMapper;
import com.siemens.crud.mapper.StudentMapper;
import com.siemens.crud.mapper.WebUserMapper;
import com.siemens.crud.model.Course;
import com.siemens.crud.model.Student;
import com.siemens.crud.model.WebUser;
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
    private WebUserMapper webUserMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseDTO> getEnrolledCourses(String studentEmail) {
        return courseMapper.toDTOs(courseRepository.findCoursesEnrolledByStudent(studentEmail));
    }

    @Override
    public List<CourseDTO> getAvailableCourses(String studentEmail) {
        return courseMapper.toDTOs(courseRepository.findCoursesNotEnrolledByStudent(studentEmail));
    }

    @Override
    public CourseDTO enrollCourse(Long courseId, String studentEmail) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        WebUser webUser = webUserRepository.findByEmail(studentEmail).
                orElseThrow(() -> new EntityNotFoundException("Student not found"));
        StudentDTO studentDTO = new StudentDTO(webUserMapper.toDTO(webUser));
        Student student = studentMapper.toEntity(studentDTO);

        student.getCourses().add(course);
        course.getStudents().add(student);

        webUserRepository.save(student);
        courseRepository.save(course);

        return courseMapper.toDTO(course);
    }

    @Override
    public boolean alreadyEnrolled(Long courseId, String studentEmail) {
        return courseRepository.isStudentEnrolledInCourse(studentEmail, courseId);
    }
}
