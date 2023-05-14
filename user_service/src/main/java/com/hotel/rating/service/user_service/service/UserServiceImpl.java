package com.hotel.rating.service.user_service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotel.rating.service.user_service.entity.Hotel;
import com.hotel.rating.service.user_service.entity.Rating;
import com.hotel.rating.service.user_service.entity.User;
import com.hotel.rating.service.user_service.exceptions.EntityNotFoundException;
import com.hotel.rating.service.user_service.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

/*    
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
*/
    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            ArrayList<Rating> userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getId(), ArrayList.class);
            user.setRatings(userRatings);
        }
        return users;
    }

/*
    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        ArrayList<Rating> userRatings = restTemplate.getForObject("http://localhost:8083/ratings/users/" + id, ArrayList.class);
        logger.info("{}", userRatings);
        return unwrapUser(user, id);
    }

    Method where call was just made to Ratings Service
    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User retrievedUser = user.get();
            List<Rating> userRatings = restTemplate.getForObject("http://localhost:8083/ratings/users/" + id, ArrayList.class);
            logger.info("{}", userRatings);
            retrievedUser.setRatings(userRatings);
            return retrievedUser;
        } else {
            throw new EntityNotFoundException(id, User.class);
        }
    }
*/

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User retrievedUser = user.get();
            //Call to Ratings Service
            Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + id, Rating[].class);
//            logger.info("User ratings for id {}: {}", Arrays.toString(userRatings));
            List<Rating> ratings = Arrays.asList(userRatings);

            //Call to Hotel Service
            List<Rating> ratingList = ratings.stream().map(rating -> {
                Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
//            logger.info("{}", ratingList);

            retrievedUser.setRatings(ratingList);
            return retrievedUser;
        } else {
            throw new EntityNotFoundException(id, User.class);
        }
    }


/*
    @Override
    public User getUser(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return unwrapUser(user, 404L);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
*/    
    
}
