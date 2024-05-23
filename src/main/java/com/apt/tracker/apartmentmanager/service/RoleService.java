package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.model.Role;
import com.apt.tracker.apartmentmanager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(int roleID) {
        return roleRepository.findById(roleID).orElse(null);
    }

    public boolean existsById(int id) {
        return roleRepository.existsById(id);
    }

    public void deleteRoleById(int roleID) {
        roleRepository.deleteById(roleID);
    }
}
