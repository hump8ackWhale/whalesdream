package com.dev.whale.repository.post;

import javax.persistence.EntityManager;

public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

}
