package com.hotel.rating.service.rating_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.rating.service.rating_service.entity.Rating;
import com.hotel.rating.service.rating_service.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    
    @PostMapping
    public ResponseEntity<Rating> createRating(@Valid @RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> findByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> findByHotelId(@PathVariable Long hotelId) {
        return new ResponseEntity<>(ratingService.getRatingsByHotelId(hotelId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> findByUserIdAndHotelId(@PathVariable Long userId, @PathVariable Long hotelId) {
        return new ResponseEntity<>(ratingService.getRatingsByUserIdAndHotelId(userId, hotelId), HttpStatus.OK);
    }

}
