package com.nexus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.entities.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    // extra methods db related operations
    // custom query methods
    // custom finder methods

    Optional<User> findByEmail(String email);

}
