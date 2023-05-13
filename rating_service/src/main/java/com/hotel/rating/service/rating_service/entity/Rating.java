package com.hotel.rating.service.rating_service.entity;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RATINGS")
@CompoundIndexes({
    @CompoundIndex(name = "user_hotel_idx", def = "{'userId': 1, 'hotelId': 1}", unique = true)
})
public class Rating {
    
    @Id
    private String id;

    @NotNull(message = "User Id cannot be blank")
    @NonNull
    @Indexed
    private Long userId;

    @NotNull(message = "Hotel Id cannot be blank")
    @NonNull
    @Indexed
    private Long hotelId;

    @DecimalMax(value = "10", message = "Ratings cannot be more than 10.")
    @DecimalMin(value = "1", message = "Ratings cannot be below 1.")
    @NotNull(message = "Ratings cannot be blank")
    @NonNull
    private double rating;

    @NotBlank(message = "Feedback cannot be blank")
    @NonNull
    private String feedback;
}
