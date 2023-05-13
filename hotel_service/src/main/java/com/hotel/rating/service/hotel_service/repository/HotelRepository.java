package com.hotel.rating.service.hotel_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.rating.service.hotel_service.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
}
