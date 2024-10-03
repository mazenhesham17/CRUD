package com.siemens.crud.mapper;

import com.siemens.crud.dto.WebUserDTO;
import com.siemens.crud.model.WebUser;
import org.mapstruct.Mapper;

import java.util.Vector;

@Mapper(componentModel = "spring")
public interface WebUserMapper {
    Vector<WebUserDTO> toDTOs(Vector<WebUser> webUsers);

    WebUserDTO toDTO(WebUser webUser);
}
