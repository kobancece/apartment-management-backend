package com.apt.tracker.apartmentmanager.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apt.tracker.apartmentmanager.model.Role;
import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.service.RoleService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatusCode;


@RestController
@RequestMapping("/apartment/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        Role savedRole = roleService.addRole(role);
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("code", HttpStatus.CREATED.value());
        successResponse.put("message", "Success.");
        successResponse.put("roleId", savedRole.getRoleID());
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{roleID}")
    public ResponseEntity<Role> getRoleById(@PathVariable int roleID) {
        Role role = roleService.findRoleById(roleID);
        return role != null ? new ResponseEntity<>(role, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRole(@RequestBody Role role) {
        int roleID = role.getRoleID();
        if (!roleService.existsById(roleID)) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("message", "Role ID does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("code", HttpStatus.OK.value());
        successResponse.put("message", "Success.");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    

    @DeleteMapping("/{roleID}/delete")
    public ResponseEntity<?> deleteRole(@PathVariable int roleID) {
        if (!roleService.existsById(roleID)) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("message", "Role ID does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        roleService.deleteRoleById(roleID);
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("code", HttpStatus.OK.value());
        successResponse.put("message", "Success.");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}

