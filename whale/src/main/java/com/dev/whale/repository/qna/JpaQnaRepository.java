package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Qna;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaQnaRepository implements QnaRepository {

    private final EntityManager em;

    public JpaQnaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertQna(Qna qna) { em.persist(qna);}

    @Override
    public List<Qna> selectMyQnaList(String usernameParam) {

        String query = "select q from Qna q where q.username = :usernameParam " +
                "order by q.createdDate desc";

        List<Qna> myQna = em.createQuery(query)
                .setParameter("usernameParam", usernameParam)
                .getResultList();

        return myQna;
    }

}
