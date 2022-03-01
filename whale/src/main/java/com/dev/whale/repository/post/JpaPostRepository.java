package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Post post) {
        em.persist(post);
    }

    @Override
    public List<Post> selectMyPostList(String usernameParam) {
        LocalDateTime date = LocalDateTime.now();
        String query = "select p from Post p where (p.createdDate <= :date and :date < p.endDate) " +
                          "or YEAR(p.createdDate) < YEAR(:date) " +
                           "and p.username = :usernameParam " +
                     "order by p.createdDate desc";

        List<Post> myPost = em.createQuery(query)
                .setParameter("usernameParam", usernameParam)
                .setParameter("date", date)
                .getResultList();

        return myPost;
    }

    @Override
    public List<Post> selectAllPostList() {
        LocalDateTime date = LocalDateTime.now();
        String query = "select p from Post p where (p.createdDate <= :date and :date < p.endDate) " +
                          "and (p.lockYn = 'N') " +
                           "or (YEAR(p.createdDate) < YEAR(:date)) " +
                     "order by p.createdDate desc";

        List<Post> allPost = em.createQuery(query)
                .setParameter("date", date)
                .getResultList();

        return allPost;
    }

    @Override
    public Post findById(int postNo) {
        return em.find(Post.class, postNo);
    }

    @Override
    public void update(Post post) {

        Post findPost = findById(post.getPostNo());

        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());
        findPost.setLockYn(post.getLockYn());
        if (findPost.getStatus().equals("I")) {
            findPost.setStatus("U");
        }

        em.persist(findPost);
    }

    @Override
    public void deleteById(int postNo) {
        em.remove(postNo);
    }
}
