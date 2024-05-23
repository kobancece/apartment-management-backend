package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, String> {
}
