package com.dev.whale.repository.main;

import com.dev.whale.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface MainRepository {
    User save(User user);
    Optional<User> findById(int userNo);
    List<User> findAll();
}
