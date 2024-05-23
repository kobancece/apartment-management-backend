package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.model.Property;
import com.apt.tracker.apartmentmanager.exception.ResourceNotFoundException;
import com.apt.tracker.apartmentmanager.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

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
                property.setAddress(propertyDetails.getAddress());
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
}
