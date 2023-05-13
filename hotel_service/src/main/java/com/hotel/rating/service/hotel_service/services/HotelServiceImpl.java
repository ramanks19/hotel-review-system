package com.hotel.rating.service.hotel_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotel.rating.service.hotel_service.entity.Hotel;
import com.hotel.rating.service.hotel_service.exceptions.EntityNotFoundException;
import com.hotel.rating.service.hotel_service.repository.HotelRepository;

import lombok.*;

@AllArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return unwrapHotel(hotel, id);
    }

    static Hotel unwrapHotel(Optional<Hotel> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Hotel.class);
    }
    
}
