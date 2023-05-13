package com.hotel.rating.service.rating_service.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }

    public EntityNotFoundException(Long userId, Long hotelId) {
        super(String.format("Rating not found for user id %d and hotel id %d", userId, hotelId));
    }
    
}
