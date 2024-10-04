package com.siemens.crud.repository;

import com.siemens.crud.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByName(String name);

    @Query("SELECT c FROM Student s JOIN s.courses c WHERE s.email = :email")
    List<Course> findCoursesEnrolledByStudent(@Param("email") String studentEmail);

    @Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT sc.id FROM Student s JOIN s.courses sc WHERE s.email = :email)")
    List<Course> findCoursesNotEnrolledByStudent(@Param("email") String studentEmail);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Student s JOIN s.courses c " +
            "WHERE s.email = :email AND c.id = :courseId")
    boolean isStudentEnrolledInCourse(@Param("email") String studentEmail,
                                      @Param("courseId") Long courseId);

}
