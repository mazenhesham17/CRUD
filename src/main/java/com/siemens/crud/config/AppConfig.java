package com.siemens.crud.config;

import com.siemens.crud.service.RegistrationService;
import com.siemens.crud.service.implementation.StudentRegistrationServiceImpl;
import com.siemens.crud.service.implementation.TeacherRegistrationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {
    @Bean
    public Map<String, RegistrationService> registrationServices(StudentRegistrationServiceImpl studentRegistrationServiceImpl,
                                                                 TeacherRegistrationServiceImpl teacherRegistrationServiceImpl) {
        return new HashMap<>();
    }
}
