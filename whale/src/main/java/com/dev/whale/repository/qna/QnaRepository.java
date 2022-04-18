package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.Reply;

import java.util.List;

public interface QnaRepository {

    void insertQna(Qna qna);

    void update(Qna qna);

    void insertReply(Reply reply);

    // 나의 QNA 리스트 조회
    List<Qna> selectMyQnaList();

    // 나의 QNA 상세 조회
    Qna findById(int qnaNo);

    // 카테고리 전체 조회
    List<Category> selectCategory();

    // QNA 코멘트 조회
    List<Reply> selectQnaReply(Integer nno);

    void deleteById(Qna qna);

    void delete(Reply reply);
}
