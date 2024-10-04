package com.siemens.crud.service.fetch;

import com.siemens.crud.dto.WebUserDTO;

public interface FetchService {
    WebUserDTO fetchUser(String email);
}
