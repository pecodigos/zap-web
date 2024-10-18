package com.pecodigos.zapweb.users.controller;

import com.pecodigos.zapweb.users.dtos.UserDTO;
import com.pecodigos.zapweb.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok().body(userService.list());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") UUID id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
