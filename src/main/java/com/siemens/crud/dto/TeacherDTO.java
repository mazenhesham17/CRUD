package com.siemens.crud.dto;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends WebUserDTO {
    public TeacherDTO(WebUserDTO other) {
        super(other);
    }

    private List<CourseDTO> courses = new ArrayList<>();

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
