package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.core.exception.model.UserNotFoundException;
import com.gabriel.ferreira.moviesbattle.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserByUsername(String username) throws UserNotFoundException;
    List<User> getAllUsers();
    void deleteUsers();
}
