package com.hotel.rating.service.hotel_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTEL")
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Hotel Name cannot be blank")
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "Location cannot be blank")
    @NonNull
    @Column(name = "LOCATION", nullable = false)
    private String location;

    @NotBlank(message = "Hotel Details cannot be blank")
    @NonNull
    @Column(name = "HOTEL_DETAILS", nullable = false)
    private String about;
    
}
