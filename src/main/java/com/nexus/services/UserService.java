package com.nexus.services;

import java.util.List;
import java.util.Optional;

import com.nexus.entities.User;
import com.nexus.forms.UserForm;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deletUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    // add more methods related to user services`
}
