package com.siemens.crud.mapper;

import com.siemens.crud.dto.FilterDTO;
import com.siemens.crud.model.WebUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilterMapper {
    List<FilterDTO> toDTOs(List<WebUser> webUsers);

    @Mapping(expression = "java(webUser.getFirstName() + ' ' + webUser.getLastName())", target = "name")
    FilterDTO toDTO(WebUser webUser);
}
