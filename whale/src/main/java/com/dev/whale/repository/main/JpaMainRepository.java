package com.dev.whale.repository.main;

import com.dev.whale.domain.model.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMainRepository implements MainRepository {

    private final EntityManager em;

    public JpaMainRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        User user = em.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public User selectUser(User user) {
        return em.find(User.class, user.getUserId());
    }

    @Override
    public void insertUser(User user) {
        em.persist(user);
    }
}
