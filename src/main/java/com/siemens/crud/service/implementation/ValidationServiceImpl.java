package com.siemens.crud.service.implementation;

import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Override
    public boolean alreadyExists(String email) {
        return webUserRepository.existsByEmail(email);
    }
}
