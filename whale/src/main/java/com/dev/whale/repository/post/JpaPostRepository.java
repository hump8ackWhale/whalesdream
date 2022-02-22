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
    public List<Post> selectMyPost(String usernameParam) {
        LocalDateTime date = LocalDateTime.now();
// AND CURRENT_TIME BETWEEN p.createdDate AND ADDDATE(p.createdDate, 1)
        List<Post> myPost = em.createQuery("SELECT p FROM Post p WHERE p.username = :usernameParam ", Post.class)
                .setParameter("usernameParam", usernameParam)
                .getResultList();

        return myPost;
    }
}
