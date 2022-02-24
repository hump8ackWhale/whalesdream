package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    /*CriteriaBuilder cb = em.getCriteriaBuilder();
    Expression<javax.sql.Timestamp> ts = cb.currentTimestamp();*/

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertPost(Post post) {
        em.persist(post);
    }

    @Override
    public List<Post> selectMyPostList(String usernameParam) {
        LocalDateTime date = LocalDateTime.now();

//date + " BETWEEN p.createdDate AND ADD_MONTHS(p.createdDate, 1)
        List<Post> myPost = em.createQuery("SELECT p FROM Post p WHERE p.username = :usernameParam")
                .setParameter("usernameParam", usernameParam)
                //.setParameter("date", date)
                .getResultList();

        return myPost;
    }
}
