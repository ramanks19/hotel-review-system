package com.hotel.rating.service.user_service.service;

import java.util.List;

import com.hotel.rating.service.user_service.entity.User;

public interface UserService {
    //create a User
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get user by id
    User getUser(Long id);

    //get user by name
//    User getUser(String userName);
}
