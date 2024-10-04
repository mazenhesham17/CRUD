package com.siemens.crud.service.registration;

import com.siemens.crud.dto.WebUserDTO;

public interface RegistrationService {
    WebUserDTO registerUser(WebUserDTO user);
}
