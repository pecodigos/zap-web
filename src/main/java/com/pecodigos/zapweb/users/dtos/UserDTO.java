package com.pecodigos.zapweb.users.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserDTO(@NotNull UUID id, @NotNull @NotBlank String name, @NotNull @NotBlank String username, @NotNull @NotBlank String email, @NotNull @NotBlank String password) {
}
