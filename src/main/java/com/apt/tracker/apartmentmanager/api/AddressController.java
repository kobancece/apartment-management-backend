package com.apt.tracker.apartmentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apt.tracker.apartmentmanager.model.Address;
import com.apt.tracker.apartmentmanager.service.AddressService;
import java.util.HashMap;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/apartment/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{userID}")
    public ResponseEntity<Map<String, Object>> addAddress(@PathVariable Integer userID, @RequestBody Address address) {
        address.setUserId(userID);
        addressService.saveAddress(address);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 201); // Using HTTP 201 for resource creation
        response.put("message", "Success.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<List<Address>> getAddressesByUserId(@PathVariable Integer userID) {
        List<Address> addresses = addressService.getAddressesByUserId(userID);
        return ResponseEntity.ok(addresses);
    }

    @DeleteMapping("/{userID}/delete/{addressID}")
    public ResponseEntity<Map<String, Object>> deleteAddress(@PathVariable Integer addressID) {
        if (!addressService.existsById(addressID)) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("message", "Address ID does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        addressService.deleteAddress(addressID);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 201);
        response.put("message", "Success.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
