package com.dev.whale.repository;

import com.dev.whale.domain.User;

import java.util.*;

public class MainRepositoryImpl implements MainRepository {

    private static Map<String, User> map = new HashMap<>();
    private static int userNo = 2;

    @Override
    public User save(User user) {
        user.setUserNo(userNo);
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

    @Override
    public List<User> getUserList() {
        return null;
    }
}
