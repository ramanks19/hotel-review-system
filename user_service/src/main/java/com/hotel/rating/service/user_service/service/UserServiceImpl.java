package com.hotel.rating.service.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotel.rating.service.user_service.entity.User;
import com.hotel.rating.service.user_service.exceptions.EntityNotFoundException;
import com.hotel.rating.service.user_service.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

//    @Override
//    public User getUser(String userName) {
//        Optional<User> user = userRepository.findByUserName(userName);
//        return unwrapUser(user, 404L);
//    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
    
    
}
