package com.dev.whale.service;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.Reply;
import com.dev.whale.repository.qna.QnaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) { this.qnaRepository = qnaRepository; }

    // 큐앤에이 리스트 조회
    public List<Qna> selectMyQnaList() { return qnaRepository.selectMyQnaList(); }

    // 큐앤에이 상세내용 조회
    public Qna findById(int qnaNo) { return qnaRepository.findById(qnaNo); }

    // 큐앤에이 카테고리 리스트 조회
    public List<Category> selectCategory() {return qnaRepository.selectCategory();}

    // 큐앤에이 등록
    public void insertQna(Qna qna){ qnaRepository.insertQna(qna); }

    // 큐앤에이 수정
    public void update(Qna qna){ qnaRepository.update(qna); }

    // 큐앤에이 삭제
    public void deleteById(Qna qna){ qnaRepository.deleteById(qna); }

    // 큐앤에이 코멘트 조회
    public List<Reply> selectQnaReply(Integer nno) {return qnaRepository.selectQnaReply(nno); }

    // 큐앤에이 댓글 등록
    public void insertReply(Reply reply){ qnaRepository.insertReply(reply); }

    // 큐앤에이 댓글 삭제
    public void delete(Reply reply){ qnaRepository.delete(reply); }


}
