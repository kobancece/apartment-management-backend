package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByRoleType(String roleType);
}

