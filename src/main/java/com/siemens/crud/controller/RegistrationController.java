package com.siemens.crud.controller;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.service.RegistrationService;
import com.siemens.crud.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private Map<String, RegistrationService> registrationServices;

    @Autowired
    private ValidationService validationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerJson(@RequestBody WebUserDTO webUserDTO) {
        return handleRegistration(webUserDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> registerForm(WebUserDTO webUserDTO) {
        return handleRegistration(webUserDTO);
    }

    private ResponseEntity<String> handleRegistration(WebUserDTO webUserDTO) {
        final String serviceName = webUserDTO.getRole().toLowerCase() + "RegistrationServiceImpl";
        RegistrationService registrationService = registrationServices.get(serviceName);
        if (registrationService == null) {
            return ResponseEntity.badRequest().body("Invalid user type!");
        }
        if (validationService.alreadyExists(webUserDTO.getEmail())) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        registrationService.registerUser(webUserDTO);
        return ResponseEntity.ok(webUserDTO.getRole() + " registered successfully!");
    }
}
