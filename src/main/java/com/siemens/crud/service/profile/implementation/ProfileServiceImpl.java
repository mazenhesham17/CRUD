package com.siemens.crud.service.profile.implementation;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.mapper.WebUserMapper;
import com.siemens.crud.model.WebUser;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.profile.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private WebUserMapper webUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public WebUserDTO updateProfile(WebUserDTO updateDTO) {
        WebUser webUser = webUserRepository.findByEmail(updateDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        webUser.setFirstName(updateDTO.getFirstName());
        webUser.setLastName(updateDTO.getLastName());
        if (!updateDTO.getPassword().isEmpty()) {
            webUser.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
        }

        webUserRepository.save(webUser);

        return webUserMapper.toDTO(webUser);

    }

    @Override
    public void deleteProfile(String email) {
        webUserRepository.deleteByEmail(email);
    }


}
