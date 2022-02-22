package com.dev.whale.service;

import com.dev.whale.domain.model.Qna;
import com.dev.whale.repository.qna.QnaRepository;

import javax.transaction.Transactional;

@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) { this.qnaRepository = qnaRepository; }

    public void interQna(Qna qna){ qnaRepository.insertQna(qna); }


}
