package com.siemens.crud.service.registration.implementation;

import com.siemens.crud.dto.StudentDTO;
import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.mapper.StudentMapper;
import com.siemens.crud.model.Student;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentRegistrationServiceImpl implements RegistrationService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public WebUserDTO registerUser(WebUserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Student student = webUserRepository.save(studentMapper.toEntity(new StudentDTO(user)));
        return studentMapper.toDTO(student);
    }
}
