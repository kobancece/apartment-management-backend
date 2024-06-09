package com.apt.tracker.apartmentmanager.api;

import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apartment/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/technicians")
    public ResponseEntity<List<User>> getTechnicians() {
        List<User> technicians = userService.findUsersByRole("technician");
        return new ResponseEntity<>(technicians, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            if (savedUser != null && savedUser.getId() != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("code", HttpStatus.CREATED.value());
                response.put("message", "Success.");
                response.put("userId", savedUser.getId());
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("code", HttpStatus.BAD_REQUEST.value());
                response.put("message", "Failed to create user.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
            response.put("message", "Internal Server Error. Unable to create user.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userService.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.OK.value());
            response.put("message", "Login successful.");
            response.put("userId", user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserById(@PathVariable int userID) {
        User user = userService.findUserById(userID);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userID}/update")
    public ResponseEntity<?> updateUser(@PathVariable Integer userID, @RequestBody User user) {
        User existingUser = userService.findUserById(userID);
        if (existingUser == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("message", "User not found with ID: " + userID);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        User updatedUser = userService.updateUser(userID, user);
        if (updatedUser == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("message", "Unable to update user with ID: " + userID);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.OK.value());
        response.put("message", "User updated successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userID}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable int userID) {
        userService.deleteUserById(userID);
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.OK.value());
        response.put("message", "Success.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

}
