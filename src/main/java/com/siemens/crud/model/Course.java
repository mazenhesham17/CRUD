package com.siemens.crud.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(unique = true)
    protected String name;

    @Column
    protected boolean active;

    @Column
    protected LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    protected Teacher lecturer;

    @ManyToMany(mappedBy = "courses")
    protected Set<Student> students = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public void setLecturer(Teacher lecturer) {
        this.lecturer = lecturer;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
