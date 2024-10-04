package com.siemens.crud.dto;

import java.util.HashSet;
import java.util.Set;

public class StudentDTO extends WebUserDTO {
    public StudentDTO(WebUserDTO other) {
        super(other);
    }

    protected Set<CourseDTO> courses = new HashSet<CourseDTO>();

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }
}
