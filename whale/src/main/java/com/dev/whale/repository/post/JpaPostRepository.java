package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;
import com.dev.whale.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    private static int firstResult = 0;

    @Override
    public void save(Post post) {
        em.persist(post);
    }

    @Override
    public Page<Post> selectMyPostList(Long lastPostId, User user, PageRequest pageRequest) {
        LocalDateTime date = LocalDateTime.now();
        String username = user.getUsername();

        if (lastPostId == 9007199254740991l) {
            firstResult = 0;
        } else {
            firstResult++;
        }

        String query = "select p " +
                         "from Post p " +
                        "where p.username = :username and (p.createdDate <= :date and :date < p.endDate) " +
                           "or YEAR(p.createdDate) < YEAR(:date) " +
                          "and p.postNo < :lastPostId " +
                     "order by p.createdDate desc";

        List<Post> post = em.createQuery(query)
                .setParameter("lastPostId", lastPostId)
                .setParameter("username", username)
                .setParameter("date", date)
                .setFirstResult(firstResult * pageRequest.getPageSize())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();

        Page<Post> posts = (Page<Post>) new PageImpl(post);

        return posts;
    }

    @Override
    public Page<Post> selectAllPostList(Long lastPostId, PageRequest pageRequest) {
        LocalDateTime date = LocalDateTime.now();

        if (lastPostId == 9007199254740991l) {
            firstResult = 0;
        } else {
            firstResult++;
        }

        String query = "select p " +
                "from Post p " +
                "where (p.createdDate <= :date and :date < p.endDate) " +
                "or YEAR(p.createdDate) < YEAR(:date) " +
                "and p.postNo < :lastPostId " +
                "order by p.createdDate desc";

        List<Post> post = em.createQuery(query)
                .setParameter("lastPostId", lastPostId)
                .setParameter("date", date)
                .setFirstResult(firstResult * pageRequest.getPageSize())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();

        Page<Post> posts = (Page<Post>) new PageImpl(post);

        return posts;
    }

    @Override
    public Post findById(int postNo) {return em.find(Post.class, postNo);}

    @Override
    public void update(Post post) {

        Post findPost = em.find(Post.class, post.getPostNo());

        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());
        findPost.setLockYn(post.getLockYn());
        if (findPost.getStatus().equals("I")) {
            findPost.setStatus("U");
        }

        em.persist(findPost);
    }

    @Override
    public void deleteById(int postNo) {em.remove(postNo);}
}
