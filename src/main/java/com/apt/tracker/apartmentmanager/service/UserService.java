package com.apt.tracker.apartmentmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserTasks(Integer userId, Long taskId) {
        User user = findUserById(userId);
        if (user != null) {
            user.setTaskId(taskId);
            return userRepository.save(user);
        }
        return null;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findUsersByRole(String role) {
        return userRepository.findByRoleType(role);
    }
    
    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRoleType(updatedUser.getRoleType());
            existingUser.setTaskId(updatedUser.getTaskId());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUserById(Integer userID) {
        userRepository.deleteById(userID);
    }

    public boolean existsById(Integer userId) {
        return userRepository.existsById(userId);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRoleType(role);
    }
    

}
