package com.dev.whale.repository;

import com.dev.whale.domain.User;

import java.util.List;
import java.util.Optional;

public interface MainRepository {
    User save(User user);
    Optional<User> findById(String id);
    List<User> findAll();
    List<User> getUserList();
}
