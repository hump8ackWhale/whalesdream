package com.dev.whale.repository.main;

import javax.persistence.EntityManager;

public class JpaMainRepository implements MainRepository {

    private final EntityManager em;

    public JpaMainRepository(EntityManager em) {
        this.em = em;
    }

}
