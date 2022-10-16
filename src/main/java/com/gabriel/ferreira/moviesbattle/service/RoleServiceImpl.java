package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Role;
import com.gabriel.ferreira.moviesbattle.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        log.info("Get all roles");
        return roleRepository.findAll();
    }

    public Role getRole(String id) {
        log.info("Get role by id {}", id);
        return roleRepository
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Role not exists"));
    }

    public Role checkRole(Role role) {
        return roleRepository
                .findByName(role.getName())
                .orElseThrow(() -> new IllegalArgumentException("Role not exists."));
    }

    public void deleteRoles() {
        log.info("Delete all roles");
        roleRepository.deleteAll();
    }

}
