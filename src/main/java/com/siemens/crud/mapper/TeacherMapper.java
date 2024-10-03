package com.siemens.crud.mapper;

import com.siemens.crud.dto.TeacherDTO;
import com.siemens.crud.model.Teacher;
import org.mapstruct.Mapper;

import java.util.Vector;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toEntity(TeacherDTO teacher);

    Vector<TeacherDTO> toDTOs(Vector<Teacher> teachers);

    TeacherDTO toDTO(Teacher teacher);
}
