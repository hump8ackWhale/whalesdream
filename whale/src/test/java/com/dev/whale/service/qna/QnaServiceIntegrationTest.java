package com.dev.whale.service.qna;

import com.dev.whale.repository.qna.QnaRepository;
import com.dev.whale.service.QnaService;
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

}
