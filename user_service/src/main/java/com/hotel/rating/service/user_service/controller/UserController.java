package com.hotel.rating.service.user_service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.rating.service.user_service.entity.User;
import com.hotel.rating.service.user_service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    int retryCount = 1;
    @GetMapping("/all")
    @Retry(name = "ratingService", fallbackMethod = "ratingFallback")
//    @CircuitBreaker(name = "ratingBreaker", fallbackMethod = "ratingFallback")
    public ResponseEntity<List<User>> getAllUsers(){
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    //Fallback method when localhost:8081/users/{userId} is facing a problem
    public ResponseEntity<User> ratingHotelFallback(Long id, Exception ex) {
        logger.info("Fallback is executed as the service is down: {}", ex.getMessage());
        User user = new User();
        user.setId(-1L); //Setting a default value as a Id
        user.setName("Fallback User");
        user.setUserName("No userName is fetched");
        user.setEmail("No email id is fetched");
        user.setAbout("This is a fallback method implementation as: " + ex.getMessage());
        user.setBirthDate(LocalDate.now());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Fallback method when localhost:8081/users/all is facing a problem
    public ResponseEntity<List<User>> ratingFallback(Exception e) {
        logger.info("Fallback is executed as the service is down: {}", e.getMessage());
        List<User> fallbackUsers = new ArrayList<>();
        fallbackUsers.add(new User("Fallback User", "No username is fetched", "No email id for a Fallback User", "A fall back user is created as RATINGS service is down", LocalDate.now()));
        return new ResponseEntity<>(fallbackUsers, HttpStatus.OK);
    }
    
}
