package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.Reply;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class JpaQnaRepository implements QnaRepository {

    private final EntityManager em;

    public JpaQnaRepository(EntityManager em) {this.em = em;}

    @Override
    public void insertQna(Qna qna) { em.persist(qna);}

    @Override
    public void insertReply(Reply reply) { em.persist(reply);}

    @Override
    public List<Qna> selectMyQnaList(String usernameParam) {
//
//        String query = "select q, c.category_name, " +
//                       "       decode((select count(*) from Reply r where q.qna_no = r.qna_no), 0, 'N', 'Y') as reply_yn " +
//                       "from   Qna q, Category c"             +
//                       "where  q.username = :usernameParam"  +
//                       "and    q.category_no = c.category_no" +
//                       "and    q.status = 'I'"                +
//                       "order  by q.createdDate desc"; 스칼라서브쿼리... 안 되네 다른 방식으로^^..

        String query = "select q, c.categoryName from Qna q, Category c "   +
                       "where  q.username = :usernameParam "   +
                       "and    q.status = 'I' "                +
                       "and    q.categoryNo = c.categoryNo "   +
                       "order  by q.createdDate desc";

        List<Object[]> myQna = em.createQuery(query)
                .setParameter("usernameParam", usernameParam)
                .getResultList();

        List<Qna> qnaList = new ArrayList<>();

        for (Object[] resultRecord : myQna) {
            Qna qna           = (Qna) resultRecord[0];
            qna.setCategoryName((String) resultRecord[1]);

            qnaList.add(qna);
        }

        return qnaList;
    }

    @Override
    public Qna selectMyQnaDetailView(Integer nno) {

        String query = "select q, c.categoryName " +
                "from  Qna q, Category c "         +
                "where q.qnaNo = :nno "            +
                "and   q.categoryNo = c.categoryNo";

        List<Object[]> myQna = em.createQuery(query)
                .setParameter("nno", nno)
                .getResultList();

        Qna qnaObject = new Qna(); // 하 모르겠다 왜 안 됨
        for (Object[] resultRecord : myQna) {
            qnaObject           = (Qna) resultRecord[0];
            qnaObject.setCategoryName((String) resultRecord[1]);
        }
        return qnaObject;
    }

    @Override
    public List<Category> selectCategory() {

        String query = "select c from Category c";

        List<Category> category = em.createQuery(query).getResultList();

        return category;
    }

    @Override
    public List<Reply> selectQnaReply(Integer nno) {

        String query = "select r from Reply r " +
                       "where r.qnaNo = :nno " +
                       "and   r.status = 'I' ";

        List<Reply> Reply = em.createQuery(query)
                            .setParameter("nno", nno)
                            .getResultList();

        return Reply;
    }

}
