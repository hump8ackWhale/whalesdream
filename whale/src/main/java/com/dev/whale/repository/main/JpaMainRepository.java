package com.dev.whale.repository.main;

import com.dev.whale.domain.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMainRepository implements MainRepository {

    private final EntityManager em;

    public JpaMainRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User join(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByName(String userName) {
        List<User> query = em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", userName)
                .getResultList();

        return query.stream().findAny();
    }
}
