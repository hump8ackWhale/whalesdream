package com.dev.whale.controller;

import com.dev.whale.domain.model.Qna;
import com.dev.whale.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/qna")
public class QnaController {

    private final QnaService QnaService;

    @Autowired
    public QnaController(QnaService QnaService) {
        this.QnaService = QnaService;
    }

    @GetMapping("/qnaListView")
    public String qnaListView(Model model, String usernameParam){

        List<Qna> qnaList = QnaService.selectMyQnaList(usernameParam);

        if (qnaList.size() > 0) {
            model.addAttribute("qnaList", qnaList);
        }

        return "qna/qnaListView";
    }

    @GetMapping("/qnaDetailView")
    public String qnaListView(Model model, Integer nno){

        Qna qnaDetail = QnaService.selectMyQnaDetailView(nno);

        model.addAttribute("qnaDetail", qnaDetail);

        return "qna/qnaDetailView";
    }
}
