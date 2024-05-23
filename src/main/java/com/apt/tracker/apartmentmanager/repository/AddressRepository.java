package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByUserId(Integer userId);
}
