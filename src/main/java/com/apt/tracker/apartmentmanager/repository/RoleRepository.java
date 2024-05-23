package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
