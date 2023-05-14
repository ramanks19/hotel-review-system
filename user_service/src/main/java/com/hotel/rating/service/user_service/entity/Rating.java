package com.hotel.rating.service.user_service.entity;

import lombok.*;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Rating {

        private String id;
        private Long userId;
        private Long hotelId;
        private double rating;
        private String feedback;
        private Hotel hotel;
    }
