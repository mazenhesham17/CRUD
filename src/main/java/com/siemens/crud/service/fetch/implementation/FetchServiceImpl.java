package com.siemens.crud.service.fetch.implementation;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.mapper.WebUserMapper;
import com.siemens.crud.model.WebUser;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.fetch.FetchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchServiceImpl implements FetchService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private WebUserMapper webUserMapper;

    @Override
    public WebUserDTO fetchUser(String email) {
        WebUser user = webUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return webUserMapper.toDTO(user);
    }
}
