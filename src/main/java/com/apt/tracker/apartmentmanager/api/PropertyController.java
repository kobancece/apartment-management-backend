package com.apt.tracker.apartmentmanager.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apt.tracker.apartmentmanager.exception.ResourceNotFoundException;
import com.apt.tracker.apartmentmanager.model.Property;
import com.apt.tracker.apartmentmanager.service.PropertyService;
import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apartment/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    @PostMapping("/add-to-user")
    public ResponseEntity<?> addPropertyToUser(@RequestBody Map<String, Integer> request) {
        Integer userId = request.get("userId");
        Long propertyId = request.get("propertyId").longValue(); // Assuming propertyId is Long

        User updatedUser = userService.updateUserProperties(userId, propertyId);
        if (updatedUser != null) {
            return ResponseEntity.ok().build();
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("message", "Unable to add property to user.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addProperty(@RequestBody Property property) {
        try {
            Property savedProperty = propertyService.addProperty(property);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("code", 201, "message", "Success.", "propertyId", savedProperty.getPropertyID()));
        } catch (DataIntegrityViolationException e) {
            // Handle the specific exception
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("code", HttpStatus.CONFLICT.value(), "message", "Data integrity violation."));
        } catch (Exception e) {
            // Handle generic exceptions
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("code", HttpStatus.INTERNAL_SERVER_ERROR.value(), "message", "An error occurred."));
        }
    }

    @PostMapping("/{propertyID}/update")
    public ResponseEntity<?> updateProperty(@PathVariable Integer propertyID, @RequestBody Property propertyDetails) {
        try {
            Property updatedProperty = propertyService.updateProperty(propertyID, propertyDetails);
            if (updatedProperty != null) {
                return ResponseEntity
                        .ok(Map.of("code", 201, "message", "Success."));
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("code", HttpStatus.NOT_FOUND.value(), "message", "Property not found."));
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("code", HttpStatus.CONFLICT.value(), "message", "Data integrity violation."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("code", HttpStatus.INTERNAL_SERVER_ERROR.value(), "message", "An error occurred."));
        }
    }

    @GetMapping("/{propertyID}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer propertyID) {
        return propertyService.getPropertyById(propertyID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping("/{propertyID}/delete")
    public ResponseEntity<?> deleteProperty(@PathVariable Integer propertyID) {
        try {
            propertyService.deleteProperty(propertyID);
            Map<String, Object> body = new HashMap<>();
            body.put("code", 201);
            body.put("message", "Success.");
            return ResponseEntity.ok(body);
        } catch (ResourceNotFoundException e) {
            Map<String, Object> body = new HashMap<>();
            body.put("code", 404);
            body.put("message", "Not Found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } catch (Exception e) {
            Map<String, Object> body = new HashMap<>();
            body.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("message", "An error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
