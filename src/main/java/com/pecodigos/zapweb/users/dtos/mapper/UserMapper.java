package com.pecodigos.zapweb.users.dtos.mapper;

import com.pecodigos.zapweb.users.dtos.UserDTO;
import com.pecodigos.zapweb.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO (User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User toEntity (UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
                .id(userDTO.id())
                .name(userDTO.name())
                .username(userDTO.username())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();
    }
}
