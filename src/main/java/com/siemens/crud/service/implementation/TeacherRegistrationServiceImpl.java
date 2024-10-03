package com.siemens.crud.service.implementation;

import com.siemens.crud.dto.TeacherDTO;
import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.mapper.TeacherMapper;
import com.siemens.crud.model.Teacher;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherRegistrationServiceImpl implements RegistrationService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public WebUserDTO registerUser(WebUserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Teacher teacher = webUserRepository.save(teacherMapper.toEntity(new TeacherDTO(user)));
        return teacherMapper.toDTO(teacher);
    }
}
