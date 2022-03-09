package com.dev.whale.repository.qna;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;

import java.util.List;

public interface QnaRepository {
    void insertQna(Qna qna);

    // 나의 QNA 리스트 조회
    List<Qna> selectMyQnaList(String usernameParam);

    // 나의 QNA 상세 조회
    Qna selectMyQnaDetailView(Integer nno);

    // 카테고리 전체 조회
    List<Category> selectCategory();
}
