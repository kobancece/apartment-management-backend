package com.apt.tracker.apartmentmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "financials")
public class Financial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer financialID;

    @Column(nullable = false)
    private Integer userID;

    @Column(nullable = false)
    private Integer propertyID;

    @Column(nullable = true)
    private Double rentAmount;

    @Column(nullable = true)
    private Double duesAmount;

    public Financial() {
    }
    /**
     * @return Integer return the financialID
     */
    public Integer getFinancialID() {
        return financialID;
    }

    /**
     * @param financialID the financialID to set
     */
    public void setFinancialID(Integer financialID) {
        this.financialID = financialID;
    }

    /**
     * @return Integer return the userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * @return Integer return the propertyID
     */
    public Integer getPropertyID() {
        return propertyID;
    }

    /**
     * @param propertyID the propertyID to set
     */
    public void setPropertyID(Integer propertyID) {
        this.propertyID = propertyID;
    }

    /**
     * @return Double return the rentAmount
     */
    public Double getRentAmount() {
        return rentAmount;
    }

    /**
     * @param rentAmount the rentAmount to set
     */
    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    /**
     * @return Double return the duesAmount
     */
    public Double getDuesAmount() {
        return duesAmount;
    }

    /**
     * @param duesAmount the duesAmount to set
     */
    public void setDuesAmount(Double duesAmount) {
        this.duesAmount = duesAmount;
    }

}
