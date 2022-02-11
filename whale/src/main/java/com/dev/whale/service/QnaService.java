package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.repository.qna.QnaRepository;

import javax.transaction.Transactional;

@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }


}
