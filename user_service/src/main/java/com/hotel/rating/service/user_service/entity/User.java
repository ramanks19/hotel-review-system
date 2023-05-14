package com.hotel.rating.service.user_service.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "User name cannot be blank")
    @NonNull
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String userName;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email cannot be blank")
    @NonNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank(message = "Bio cannot be empty")
    @NonNull
    @Column(name = "BIO", nullable = false)
    private String about;

    @Past(message = "The birth date must be in Past")
    @NonNull
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate birthDate;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
