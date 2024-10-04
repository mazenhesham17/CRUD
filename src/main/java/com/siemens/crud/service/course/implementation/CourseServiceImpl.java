package com.siemens.crud.service.course.implementation;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.dto.TeacherDTO;
import com.siemens.crud.mapper.CourseMapper;
import com.siemens.crud.mapper.TeacherMapper;
import com.siemens.crud.mapper.WebUserMapper;
import com.siemens.crud.model.Course;
import com.siemens.crud.model.Teacher;
import com.siemens.crud.model.WebUser;
import com.siemens.crud.repository.CourseRepository;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.course.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private WebUserMapper webUserMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Optional<CourseDTO> getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(courseMapper::toDTO);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseMapper.toDTOs(courseRepository.findAll());
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO, String lecturerUsername) {
        WebUser webUser = webUserRepository.findByEmail(lecturerUsername).
                orElseThrow(() -> new EntityNotFoundException("Lecturer not found"));
        TeacherDTO lecturer = new TeacherDTO(webUserMapper.toDTO(webUser));
        Teacher teacher = teacherMapper.toEntity(lecturer);
        Course course = courseMapper.toEntity(courseDTO);
        course.setLecturer(teacher);
        course = courseRepository.save(course);
        teacher.getCourses().add(course);
        webUserRepository.save(teacher);
        return courseMapper.toDTO(course);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course not found")
        );
        course.setName(courseDTO.getName());
        course.setActive(courseDTO.isActive());
        course.setRegistrationDate(courseDTO.getRegistrationDate());
        course = courseRepository.save(course);

        return courseMapper.toDTO(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean alreadyExists(String courseName) {
        return courseRepository.existsByName(courseName);
    }
}
