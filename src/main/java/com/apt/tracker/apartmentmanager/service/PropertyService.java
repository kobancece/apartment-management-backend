package com.apt.tracker.apartmentmanager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.apt.tracker.apartmentmanager.model.Property;
import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.exception.ResourceNotFoundException;
import com.apt.tracker.apartmentmanager.repository.PropertyRepository;
import com.apt.tracker.apartmentmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> getPropertyById(Integer propertyID) {
        return propertyRepository.findById(propertyID);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property updateProperty(Integer propertyID, Property propertyDetails) {
        return propertyRepository.findById(propertyID)
            .map(property -> {
                property.setPropertyName(propertyDetails.getPropertyName());
                property.setDescription(propertyDetails.getDescription());
                property.setFlatNumber(propertyDetails.getFlatNumber());
                property.setSize(propertyDetails.getSize());
                property.setNumberOfRooms(propertyDetails.getNumberOfRooms());
                property.setNumberOfBathrooms(propertyDetails.getNumberOfBathrooms());
                property.setRent(propertyDetails.getRent());
                property.setRentalStatus(propertyDetails.getRentalStatus());
                property.setNumberOfBalconies(propertyDetails.getNumberOfBalconies());
                property.setInternetStatus(propertyDetails.getInternetStatus());
                property.setInfrastructure(propertyDetails.getInfrastructure());
                return propertyRepository.save(property);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Property not found with id " + propertyID));
    }

    public void deleteProperty(Integer propertyID) {
        propertyRepository.deleteById(propertyID);
    }

    public User updateUserProperties(Integer userId, Long propertyId) {
        User user = userService.findUserById(userId);
        if (user != null && propertyId != null) {
            List<Long> propertyIds = new ArrayList<>();
            try {
                if (user.getProperties() != null) {
                    propertyIds = objectMapper.readValue(user.getProperties(), new TypeReference<List<Long>>() {});
                }
                propertyIds.add(propertyId);
                user.setProperties(objectMapper.writeValueAsString(propertyIds));
                return userService.saveUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void removePropertyFromUser(Integer userId, Long propertyId) {
        User user = userService.findUserById(userId);
        if (user != null && propertyId != null) {
            List<Long> propertyIds = new ArrayList<>();
            try {
                if (user.getProperties() != null) {
                    propertyIds = objectMapper.readValue(user.getProperties(), new TypeReference<List<Long>>() {});
                }
                propertyIds.remove(propertyId);
                user.setProperties(objectMapper.writeValueAsString(propertyIds));
                userService.saveUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addPropertyToUser(Integer userId, Long propertyId) {
        updateUserProperties(userId, propertyId);
    }
}
