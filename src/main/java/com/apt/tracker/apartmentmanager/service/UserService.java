package com.apt.tracker.apartmentmanager.service;

import java.util.List;

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

    public User findUserById(int userID) {
        return userRepository.findById(userID).orElse(null);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(int userID, User userDetails) {
        return userRepository.findById(userID).map(existingUser -> {
            existingUser.setName(userDetails.getName());
            existingUser.setSurname(userDetails.getSurname());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhoneNumber(userDetails.getPhoneNumber());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setFlatList(userDetails.getFlatList());
            existingUser.setRoleType(userDetails.getRoleType());
            return userRepository.save(existingUser);
        }).orElse(null);
    }

    public void deleteUserById(int userID) {
        userRepository.deleteById(userID);
    }

    public boolean existsById(Integer userId) {
        return userRepository.existsById(userId);
    }

}
