package com.siemens.crud.repository;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    Optional<WebUser> findByEmail(String username);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
