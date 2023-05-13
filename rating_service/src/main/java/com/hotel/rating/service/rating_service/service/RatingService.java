package com.hotel.rating.service.rating_service.service;

import java.util.List;

import com.hotel.rating.service.rating_service.entity.Rating;

public interface RatingService {
    //create Rating
    Rating createRating(Rating rating);

    //Get all ratings
    List<Rating> getAllRatings();

    //Get all ratings by UserId
    List<Rating> getRatingByUserId(Long userId);

    //Get all ratings for a HotelId
    List<Rating> getRatingsByHotelId(Long hotelId);

    //Get rating given by a user for a hotel
    List<Rating> getRatingsByUserIdAndHotelId(Long userId, Long hotelId);
    
}
