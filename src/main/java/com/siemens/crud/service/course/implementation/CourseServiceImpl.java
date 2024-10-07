package com.siemens.crud.service.course.implementation;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.mapper.CourseMapper;
import com.siemens.crud.model.Course;
import com.siemens.crud.model.Teacher;
import com.siemens.crud.repository.CourseRepository;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.course.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        return courseMapper.toDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseMapper.toDTOs(courseRepository.findAll());
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO, Long lecturerId) {
        Teacher teacher = webUserRepository.findTeacherById(lecturerId)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer not found"));
        Course course = courseMapper.toEntity(courseDTO);
        course.setLecturer(teacher);
        course = courseRepository.save(course);
        teacher.getCourses().add(course);
        webUserRepository.save(teacher);
        return courseMapper.toDTO(course);
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new EntityNotFoundException("Course not found")
        );
        course.setName(courseDTO.getName());
        course.setActive(courseDTO.isActive());
        course.setRegistrationDate(courseDTO.getRegistrationDate());
        course = courseRepository.save(course);

        return courseMapper.toDTO(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public boolean alreadyExists(String courseName) {
        return courseRepository.existsByName(courseName);
    }
}
