package com.nexus.config;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.entities.User;
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
