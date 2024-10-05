package com.siemens.crud.service.filter.implementation;

import com.siemens.crud.dto.FilterDTO;
import com.siemens.crud.mapper.FilterMapper;
import com.siemens.crud.repository.WebUserRepository;
import com.siemens.crud.service.filter.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private FilterMapper filterMapper;

    @Override
    public List<FilterDTO> getByRole(String role) {
        return filterMapper.toDTOs(webUserRepository.findAllByRoleIgnoreCase(role));
    }

    @Override
    public List<FilterDTO> getByRoleAndName(String role, String name) {
        return filterMapper.toDTOs(webUserRepository.findAllByRoleIgnoreCaseAndNameContainingIgnoreCase(role, name));
    }

    @Override
    public List<FilterDTO> getByRoleAndEmail(String role, String email) {
        return filterMapper.toDTOs(webUserRepository.findAllByRoleIgnoreCaseAndEmailContainingIgnoreCase(role, email));
    }
}
