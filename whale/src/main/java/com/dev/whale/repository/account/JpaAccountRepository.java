package com.dev.whale.repository.account;

import com.dev.whale.domain.model.User;

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

        return query.stream().findAny();    // 일치하는 값 1개 return
    }

    @Override
    public int updateUserTempPassword(String str, Long id) {
        return em.createQuery("update User u set u.password = :str, u.issueYn = 'Y' where u.id = :id ")
                .setParameter("str", str)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void changePw(String username, String encodeNewPw) {
        Optional<User> user = findByName(username);
        user.get().setPassword(encodeNewPw);    // 실패 , optional 값 수정하는 방법 알아봐야함
        em.persist(user);
    }

}
