package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.model.Financial;
import com.apt.tracker.apartmentmanager.repository.FinancialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialService {

    @Autowired
    private FinancialRepository financialRepository;

    public void trackRent(Integer userID, Integer propertyID, Double rentAmount) {
        Financial financial = new Financial();
        financial.setUserID(userID);
        financial.setPropertyID(propertyID);
        financial.setRentAmount(rentAmount);
        financialRepository.save(financial);
    }

    public void trackDues(Integer userID, Integer propertyID, Double duesAmount) {
        Financial financial = new Financial();
        financial.setUserID(userID);
        financial.setPropertyID(propertyID);
        financial.setDuesAmount(duesAmount);
        financialRepository.save(financial);
    }
}
