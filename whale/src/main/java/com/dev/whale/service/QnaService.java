package com.dev.whale.service;

import com.dev.whale.domain.model.Qna;
import com.dev.whale.repository.qna.QnaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) { this.qnaRepository = qnaRepository; }

    // 큐앤에이 리스트 조회
    public List<Qna> selectMyQnaList(String usernameParam) { return qnaRepository.selectMyQnaList(usernameParam); }

    // 큐앤에이 상세내용 조회
    public Qna selectMyQnaDetailView(Integer nno) { return qnaRepository.selectMyQnaDetailView(nno); }

    public void interQna(Qna qna){ qnaRepository.insertQna(qna); }


}
