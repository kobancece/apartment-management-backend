package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
