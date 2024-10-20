package com.pecodigos.zapweb.users.dtos.mapper;

import com.pecodigos.zapweb.users.dtos.UserDTO;
import com.pecodigos.zapweb.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
