package com.pecodigos.zapweb.users.service;

import com.pecodigos.zapweb.users.dtos.UserDTO;
import com.pecodigos.zapweb.users.dtos.mapper.UserMapper;
import com.pecodigos.zapweb.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("No user with that name."));
    }

    public List<UserDTO> list() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO create(UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public UserDTO update(UUID id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(data -> {
                    data.setName(userDTO.name());
                    data.setUsername(userDTO.username());
                    data.setEmail(userDTO.email());
                    data.setPassword(userDTO.password());

                    return userMapper.toDTO(userRepository.save(data));
                }).orElseThrow(() -> new NoSuchElementException("No user found with that ID."));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
