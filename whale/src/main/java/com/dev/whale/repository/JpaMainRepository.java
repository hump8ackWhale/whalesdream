package com.dev.whale.repository;

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
    public Optional<User> findById(String id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
