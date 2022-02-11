package com.dev.whale.repository.qna;

import javax.persistence.EntityManager;

public class JpaQnaRepository implements QnaRepository {

    private final EntityManager em;

    public JpaQnaRepository(EntityManager em) {
        this.em = em;
    }

}
