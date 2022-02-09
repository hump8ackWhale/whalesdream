package com.dev.whale.repository.myPage;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMyPageRepository implements MyPageRepository {

    private final EntityManager em;

    public JpaMyPageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User userInfo(int userNo) {
        User user = em.find(User.class, userNo);
        return user;
    }
}
