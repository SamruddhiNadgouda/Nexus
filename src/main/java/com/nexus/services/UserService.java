package com.nexus.services;

import java.util.List;
import java.util.Optional;

import com.nexus.entities.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deletUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();
    User getUserByEmail(String email);
    // add more methods related to user services`

    public User findByEmail(String username);
}
