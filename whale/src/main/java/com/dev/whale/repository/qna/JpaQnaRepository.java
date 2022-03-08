package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.User;

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

        String query = "select q, c.category_name, " +
                       "       decode((select count(*) from Reply r where q.qna_no = r.qna_no), 0, 'N', 'Y') as reply_yn " +
                       "from   Qna q, Category c" +
                       "where  q.username = :usernameParam " +
                       "and    q.category_no = c.category_no" +
                       "order  by q.createdDate desc";

        List<Qna> myQna = em.createQuery(query)
                .setParameter("usernameParam", usernameParam)
                .getResultList();

        return myQna;
    }

    @Override
    public Qna selectMyQnaDetailView(Integer nno) {

        return em.createQuery("select q, c.category_name from Qna q, Category c where q.qna_no = :nno and q.category_no = c.category_no", Qna.class)
                .setParameter("nno", nno)
                .getSingleResult();

    }

}
