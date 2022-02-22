package com.dev.whale.service.qna;

import com.dev.whale.domain.model.Qna;
import com.dev.whale.repository.qna.QnaRepository;
import com.dev.whale.service.QnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class QnaServiceIntegrationTest {

    @Autowired
    QnaService qnaService;
    @Autowired
    QnaRepository qnaRepository;

    @Test
    public void insertQna(){

        Qna qna = new Qna();

        qna.setUsername("kimzoo");
        qna.setCategoryNo(1);
        qna.setTitle("Q&A TITLE TEST");
        qna.setContent("TEST Q&A");
        qnaService.interQna(qna);

        System.out.print("작성일 : " + qna.getCreatedDate());
    }

    @Test
    public void modifyQna(){

    }

}
