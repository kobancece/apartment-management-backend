package com.apt.tracker.apartmentmanager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
            List<Long> taskIds = new ArrayList<>();
            try {
                if (user.getTasks() != null) {
                    taskIds = objectMapper.readValue(user.getTasks(), new TypeReference<List<Long>>() {});
                }
                taskIds.add(taskId);
                user.setTasks(objectMapper.writeValueAsString(taskIds));
                return userRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public User updateUserProperties(Integer userId, Long propertyId) {
        User user = findUserById(userId);
        if (user != null && propertyId != null) {
            List<Long> propertyIds = new ArrayList<>();
            try {
                if (user.getProperties() != null) {
                    propertyIds = objectMapper.readValue(user.getProperties(), new TypeReference<List<Long>>() {});
                }
                propertyIds.add(propertyId);
                user.setProperties(objectMapper.writeValueAsString(propertyIds));
                return userRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
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
            existingUser.setTasks(updatedUser.getTasks());
            existingUser.setProperties(updatedUser.getProperties());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void addPropertyToUser(Integer userId, Long propertyId) {
        updateUserProperties(userId, propertyId);
    }

    public void removePropertyFromUser(Integer userId, Long propertyId) {
        User user = findUserById(userId);
        if (user != null && propertyId != null) {
            List<Long> propertyIds = new ArrayList<>();
            try {
                if (user.getProperties() != null) {
                    propertyIds = objectMapper.readValue(user.getProperties(), new TypeReference<List<Long>>() {});
                }
                propertyIds.remove(propertyId);
                user.setProperties(objectMapper.writeValueAsString(propertyIds));
                userRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
