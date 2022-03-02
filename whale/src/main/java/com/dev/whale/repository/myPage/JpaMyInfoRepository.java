package com.dev.whale.repository.myPage;

import com.dev.whale.domain.model.User;

import javax.persistence.EntityManager;

public class JpaMyInfoRepository implements MyInfoRepository {

    private final EntityManager em;

    public JpaMyInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User selectUserInfo(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
