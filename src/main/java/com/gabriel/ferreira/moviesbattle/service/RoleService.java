package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Role getRole(String id);
    Role checkRole(Role role);
    void deleteRoles();
}
