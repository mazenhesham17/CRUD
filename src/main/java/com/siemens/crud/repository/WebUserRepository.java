package com.siemens.crud.repository;

import com.siemens.crud.model.Student;
import com.siemens.crud.model.Teacher;
import com.siemens.crud.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    Optional<WebUser> findByEmail(String username);

    @Query("SELECT t FROM Teacher t WHERE t.id = :id")
    Optional<Teacher> findTeacherById(@Param("id") Long id);

    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Optional<Student> findStudentById(@Param("id") Long id);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    List<WebUser> findAllByRoleIgnoreCase(String role);

    List<WebUser> findAllByRoleIgnoreCaseAndEmailContainingIgnoreCase(String role, String email);

    @Query("SELECT w FROM WebUser w WHERE LOWER(w.role) = LOWER(:role) AND LOWER(CONCAT(w.firstName, ' ', w.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<WebUser> findAllByRoleIgnoreCaseAndNameContainingIgnoreCase(@Param("role") String role, @Param("name") String name);


}
