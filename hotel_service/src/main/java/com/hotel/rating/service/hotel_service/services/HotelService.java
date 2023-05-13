package com.hotel.rating.service.hotel_service.services;

import java.util.List;

import com.hotel.rating.service.hotel_service.entity.Hotel;

public interface HotelService {
    //create a Hotel
    Hotel saveHotel(Hotel hotel);

    //get all hotels
    List<Hotel> getAllHotels();

    //get hotel by id
    Hotel getHotel(Long id);
}
