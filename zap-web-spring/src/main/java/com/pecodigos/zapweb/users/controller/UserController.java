package com.pecodigos.zapweb.users.controller;

import com.pecodigos.zapweb.security.auth.JwtUtil;
import com.pecodigos.zapweb.users.dtos.UserDTO;
import com.pecodigos.zapweb.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Tag(name = "Users API", description = "Endpoints for user management")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Get one user", description = "Retrieves a user by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok().body(userService.list());
    }

    @Operation(summary = "Register user", description = "Creates a new user")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDTO));
    }

    @Operation(summary = "Login user", description = "Authenticates a user and returns a token")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password()));
            String token = jwtUtil.generateToken(auth.getName());

            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Update user", description = "Updates an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") UUID id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userDTO));
    }

    @Operation(summary = "Delete user", description = "Deletes a user by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
