package com.dev.whale.repository;

import com.dev.whale.domain.model.User;

import java.util.*;

public class MainRepositoryImpl implements MainRepository {

    Map<String, User> map = new HashMap<>();

    @Override
    public User save(User user) {
        map.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

    public void clearStore() {
        map.clear();
    }
}
