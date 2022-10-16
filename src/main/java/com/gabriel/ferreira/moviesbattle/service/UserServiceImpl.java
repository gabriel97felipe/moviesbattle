package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.core.exception.model.UserNotFoundException;
import com.gabriel.ferreira.moviesbattle.model.Role;
import com.gabriel.ferreira.moviesbattle.model.User;
import com.gabriel.ferreira.moviesbattle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    public final String DEFAULT_ROLE = "USER";
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        log.info("User {} found in database", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User user) {
        log.info("Creating new user {}", user.getUsername());
        user.setRole(createRoleForNewUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role createRoleForNewUser() {
        return roleService.checkRole(new Role(DEFAULT_ROLE));
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        log.info("Get user by username {}", username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not exists!", "Query did not return user."));
    }

    @Transactional
    public void deleteUsers() {
        log.info("Delete users");
        userRepository.deleteAll();
    }

}
