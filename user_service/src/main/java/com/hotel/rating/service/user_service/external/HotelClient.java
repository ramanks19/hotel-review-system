package com.hotel.rating.service.user_service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.rating.service.user_service.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {
    
    @GetMapping("/hotels/{id}")
    Hotel getHotel(@PathVariable Long id);
}
