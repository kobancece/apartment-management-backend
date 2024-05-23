package com.apt.tracker.apartmentmanager.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // You can also add constructors that accept the cause of the exception
}
