package com.siemens.crud.mapper;

import com.siemens.crud.dto.CourseDTO;
import com.siemens.crud.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO courseDTO);

    List<CourseDTO> toDTOs(List<Course> courses);

    CourseDTO toDTO(Course course);
}
