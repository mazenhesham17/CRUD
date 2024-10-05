package com.siemens.crud.service.profile;

import com.siemens.crud.dto.WebUserDTO;

public interface ProfileService {

    WebUserDTO updateProfile(WebUserDTO webUserDTO);

    WebUserDTO updateProfileById(WebUserDTO webUserDTO);

    void deleteProfile(Long id);

    void deleteProfile(String email);
}
