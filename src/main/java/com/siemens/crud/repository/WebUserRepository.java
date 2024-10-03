package com.siemens.crud.repository;

import com.siemens.crud.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    Optional<WebUser> findByEmail(String username);

    boolean existsByEmail(String email);
}
