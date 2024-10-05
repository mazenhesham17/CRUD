package com.siemens.crud.repository;

import com.siemens.crud.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    Optional<WebUser> findByEmail(String username);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    List<WebUser> findAllByRoleIgnoreCase(String role);

    List<WebUser> findAllByRoleIgnoreCaseAndEmailContainingIgnoreCase(String role, String email);

    @Query("SELECT w FROM WebUser w WHERE LOWER(w.role) = LOWER(:role) AND LOWER(CONCAT(w.firstName, ' ', w.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<WebUser> findAllByRoleIgnoreCaseAndNameContainingIgnoreCase(@Param("role") String role, @Param("name") String name);


}
