package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.model.Address;
import com.apt.tracker.apartmentmanager.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAddressesByUserId(Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    public boolean existsById(Integer userId) {
        return addressRepository.existsById(userId);
    }

    
}
