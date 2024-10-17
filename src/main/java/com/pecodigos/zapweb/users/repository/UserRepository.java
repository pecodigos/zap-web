package com.pecodigos.zapweb.users.repository;

import com.pecodigos.zapweb.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
