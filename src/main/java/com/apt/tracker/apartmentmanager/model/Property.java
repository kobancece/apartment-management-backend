package com.apt.tracker.apartmentmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @Column(name = "propertyID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyID;

    @ManyToOne
    @JoinColumn(name = "addressID", referencedColumnName = "addressID")
    private Address address;

    @Column(nullable = false)
    private String propertyName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Integer flatNumber;
    
    @Column(nullable = false)
    private Double size;
    
    @Column(nullable = true)
    private Integer numberOfRooms;
    
    @Column(nullable = true)
    private Integer numberOfBathrooms;
    
    @Column(nullable = true)
    private Double rent;
    
    @Column(nullable = true)
    private String rentalStatus;
    
    @Column(nullable = true)
    private Integer numberOfBalconies;
    
    @Column(nullable = true)
    private String internetStatus;
    
    @Column(nullable = true)
    private String infrastructure;

    public Property() {
    }

    public Property(String propertyName, Address address, String description) {
        this.propertyName = propertyName;
        this.address = address;
        this.description = description;
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
     * @return String return the propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @return Address return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Integer return the flatNumber
     */
    public Integer getFlatNumber() {
        return flatNumber;
    }

    /**
     * @param flatNumber the flatNumber to set
     */
    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    /**
     * @return Double return the size
     */
    public Double getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * @return Integer return the numberOfRooms
     */
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * @param numberOfRooms the numberOfRooms to set
     */
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * @return Integer return the numberOfBathrooms
     */
    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * @param numberOfBathrooms the numberOfBathrooms to set
     */
    public void setNumberOfBathrooms(Integer numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * @return Double return the rent
     */
    public Double getRent() {
        return rent;
    }

    /**
     * @param rent the rent to set
     */
    public void setRent(Double rent) {
        this.rent = rent;
    }

    /**
     * @return String return the rentalStatus
     */
    public String getRentalStatus() {
        return rentalStatus;
    }

    /**
     * @param rentalStatus the rentalStatus to set
     */
    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    /**
     * @return Integer return the numberOfBalconies
     */
    public Integer getNumberOfBalconies() {
        return numberOfBalconies;
    }

    /**
     * @param numberOfBalconies the numberOfBalconies to set
     */
    public void setNumberOfBalconies(Integer numberOfBalconies) {
        this.numberOfBalconies = numberOfBalconies;
    }

    /**
     * @return String return the internetStatus
     */
    public String getInternetStatus() {
        return internetStatus;
    }

    /**
     * @param internetStatus the internetStatus to set
     */
    public void setInternetStatus(String internetStatus) {
        this.internetStatus = internetStatus;
    }

    /**
     * @return String return the infrastructure
     */
    public String getInfrastructure() {
        return infrastructure;
    }

    /**
     * @param infrastructure the infrastructure to set
     */
    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

}
