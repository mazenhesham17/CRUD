package com.siemens.crud.service.profile;

import com.siemens.crud.dto.WebUserDTO;

public interface ProfileService {

    WebUserDTO updateProfile(WebUserDTO webUserDTO);

    void deleteProfile(String email);
}
