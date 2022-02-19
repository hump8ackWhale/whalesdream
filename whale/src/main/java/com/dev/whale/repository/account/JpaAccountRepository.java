package com.dev.whale.repository.account;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository {

    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
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
