package com.phredd.library.librarysystem.repository;

import com.phredd.library.librarysystem.model.User;

import java.util.List;
import java.util.Optional;



public interface UserRepository {

    void save(User user);

    void deleteById(String id);

    Optional<User> findById(String id);

    List<User> findByName(String name);

    List<User> findAll();
}