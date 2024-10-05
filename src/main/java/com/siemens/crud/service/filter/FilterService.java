package com.siemens.crud.service.filter;

import com.siemens.crud.dto.FilterDTO;

import java.util.List;

public interface FilterService {
    List<FilterDTO> getByRole(String role);

    List<FilterDTO> getByRoleAndName(String role, String name);

    List<FilterDTO> getByRoleAndEmail(String role, String email);
}
