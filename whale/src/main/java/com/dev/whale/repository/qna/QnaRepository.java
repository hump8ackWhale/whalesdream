package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Qna;

import java.util.List;

public interface QnaRepository {
    void insertQna(Qna qna);

    // 나의 QNA 리스트 조회
    List<Qna> selectMyQnaList(String usernameParam);
}
