package com.siemens.crud.service;

import com.siemens.crud.dto.WebUserDTO;

public interface RegistrationService {
    WebUserDTO registerUser(WebUserDTO user);
}
