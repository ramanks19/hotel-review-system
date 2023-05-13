package com.hotel.rating.service.hotel_service.controller;

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

import com.hotel.rating.service.hotel_service.entity.Hotel;
import com.hotel.rating.service.hotel_service.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    
    @Autowired
    HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.saveHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Long id) {
        return new ResponseEntity<>(hotelService.getHotel(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }
}
