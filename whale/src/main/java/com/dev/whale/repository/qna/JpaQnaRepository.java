package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.Reply;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaQnaRepository implements QnaRepository {

    private final EntityManager em;

    public JpaQnaRepository(EntityManager em) {this.em = em;}

    @Override
    public void insertQna(Qna qna) { em.persist(qna);}

    @Override
    public void update(Qna qna) {

        Qna findQna = em.find(Qna.class, qna.getQnaNo());

        findQna.setTitle(qna.getTitle());
        findQna.setContent(qna.getContent());
        if (findQna.getStatus().equals("I")) {
            findQna.setStatus("U");
        }
        em.persist(findQna); // 영속성 context
    }

    @Override
    public void insertReply(Reply reply) { em.persist(reply);}

    @Override
    public List<Qna> selectMyQnaList() {

        String query = "select q from Qna q "        +
                       "left join fetch q.category " +
                       "order  by q.createdDate desc";

        List<Qna> qnaList = em.createQuery(query)
                .getResultList();

        return qnaList;
    }

    @Override
    public Qna findById(int qnaNo) {return em.find(Qna.class, qnaNo);}

    @Override
    public List<Category> selectCategory() {

        String query = "select c from Category c";

        List<Category> category = em.createQuery(query).getResultList();

        return category;
    }

    @Override
    public List<Reply> selectQnaReply(Integer nno) {

        String query = "select r from Reply r " +
                       "where r.qnaNo = :nno " ;

        List<Reply> Reply = em.createQuery(query)
                            .setParameter("nno", nno)
                            .getResultList();

        return Reply;
    }

    @Override
    public void deleteById(Qna qna) {
        Qna transientQna = em.find(Qna.class, qna.getQnaNo());
        em.remove(transientQna);}

    @Override
    public void delete(Reply reply) {
        Reply transientReply = em.find(Reply.class, reply.getReplyNo());
        em.remove(transientReply);
    }
}
