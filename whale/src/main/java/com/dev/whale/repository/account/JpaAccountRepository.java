package com.dev.whale.repository.account;

import com.dev.whale.domain.model.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository {

    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> findByName(String username) {
        List<User> query = em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return query.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> query = em.createQuery("select u from User u where u.email = :email")
                .setParameter("email", email)
                .getResultList();

        return query.stream().findAny();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User join(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public int updateUserTempPassword(String str, Long id) {
        return em.createQuery("update User u set u.password = :str, u.issueYn = 'Y' where u.id = :id ")
                .setParameter("str", str)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void changePw(Long id, String encodeNewPw) {
        User user = em.find(User.class, id);

        user.setPassword(encodeNewPw);
        user.setIssueYn("N");

        em.persist(user);
    }

    @Override
    public void updateTerm(Long id) {
        User user = em.find(User.class, id);
        LocalDateTime date = LocalDateTime.now();

        user.setTermDate(date);
        //user.setTermYn("Y");
        user.setEnabled(false);

        em.persist(user);
    }

}
