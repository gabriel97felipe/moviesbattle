package com.gabriel.ferreira.moviesbattle.controller;
import com.gabriel.ferreira.moviesbattle.model.Role;
import com.gabriel.ferreira.moviesbattle.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody(required = true)  Role role){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getByCode(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRole(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllRoles(){
        roleService.deleteRoles();
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
