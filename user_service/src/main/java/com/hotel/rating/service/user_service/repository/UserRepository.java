package com.hotel.rating.service.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.rating.service.user_service.entity.User;

/*
 * JpaRepository is used in place of CrudRepository as:
 * CrudRepository is the basic interface for CRUD operations, providing basic methods 
 * like save(), findById(), findAll(), deleteById(), and so on.
 * JpaRepository extends CrudRepository and provides additional methods for paging and 
 * sorting results, as well as various query methods.
 */

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUserName(String userName);
}
