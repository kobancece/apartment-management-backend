package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialRepository extends JpaRepository<Financial, Integer> {
}
