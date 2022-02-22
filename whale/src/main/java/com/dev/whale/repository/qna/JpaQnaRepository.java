package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Qna;

import javax.persistence.EntityManager;

public class JpaQnaRepository implements QnaRepository {

    private final EntityManager em;

    public JpaQnaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertQna(Qna qna) { em.persist(qna);}

}
