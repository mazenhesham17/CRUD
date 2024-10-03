package com.siemens.crud.mapper;

import com.siemens.crud.dto.StudentDTO;
import com.siemens.crud.model.Student;
import org.mapstruct.Mapper;

import java.util.Vector;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentDTO studentDTO);

    Vector<StudentDTO> toDTOs(Vector<Student> students);

    StudentDTO toDTO(Student student);
}
