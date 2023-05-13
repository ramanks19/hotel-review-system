package com.hotel.rating.service.user_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RATINGS", uniqueConstraints = {@UniqueConstraint(name = "user_hotel_idx", columnNames = {"USER_ID", "HOTEL_ID"})})
public class Rating {
    
    @Id
    private String id;
    
    @NotBlank(message = "User Id cannot be blank")
    @NonNull
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @NotBlank(message = "Hotel Id cannot be blank")
    @NonNull
    @Column(name = "HOTEL_ID", nullable = false)
    private Long hotelId;

    @DecimalMax(value = "10", message = "Rating cannot be more than 10")
    @DecimalMin(value = "1", message = "Rating cannot be below 1")
    @NotBlank(message = "Rating cannot be blank")
    @Column(name = "RATING", nullable = false)
    private double rating;

    @NotBlank(message = "Feedback cannot be blank")
    @NonNull
    @Column(name = "FEEDBACK", nullable = false)
    private String feedback;
}
