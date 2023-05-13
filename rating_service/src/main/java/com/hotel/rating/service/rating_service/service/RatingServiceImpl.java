package com.hotel.rating.service.rating_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.rating.service.rating_service.entity.Rating;
import com.hotel.rating.service.rating_service.exceptions.EntityNotFoundException;
import com.hotel.rating.service.rating_service.repository.RatingRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(Long userId) {
        List<Rating> userRating = ratingRepository.findByUserId(userId);
        if (userRating.isEmpty()) {
            throw new EntityNotFoundException(userId, Rating.class);
        }
        return userRating;
    }

    @Override
    public List<Rating> getRatingsByHotelId(Long hotelId) {
        List<Rating> hotelRating = ratingRepository.findByHotelId(hotelId);
        if (hotelRating.isEmpty()) {
            throw new EntityNotFoundException(hotelId, Rating.class);
        }
        return hotelRating;
    }

    @Override
    public List<Rating> getRatingsByUserIdAndHotelId(Long userId, Long hotelId) {
        List<Rating> userRating = ratingRepository.findByUserIdAndHotelId(userId, hotelId);
        if (userRating.isEmpty()) {
            throw new EntityNotFoundException(userId, hotelId);
        }
        return userRating;
    }
    
}
