package com.hotel.rating.service.rating_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.rating.service.rating_service.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(Long hotelId);

    List<Rating> findByUserIdAndHotelId(Long userId, Long HotelId);
}
