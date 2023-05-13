package com.hotel.rating.service.hotel_service.exceptions;

public class EntityNotFoundException extends RuntimeException{
    
    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }
}
