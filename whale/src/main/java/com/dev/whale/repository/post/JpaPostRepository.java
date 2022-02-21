package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        List<Post> myPost = em.createQuery("SELECT p FROM Post p WHERE p.username = :usernameParam" +
                                                    " AND " + date + " BETWEEN p.createdDate AND ADD_MONTHS(p.createdDate, 1)", Post.class)
                .setParameter("usernameParam", usernameParam)
                .getResultList();

        return myPost;
    }
}
