package com.dev.whale.controller;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.User;
import com.dev.whale.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String qnaListView(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Qna> qnaList = QnaService.selectMyQnaList(auth.getName());

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

    @GetMapping("/qnaEnrollForm")
    public String qnaEnrollForm(Model model){

        List<Category> category = QnaService.selectCategory();

        model.addAttribute("categoryList", category);

        return "qna/qnaEnrollForm";
    }

    @GetMapping("/insertQna")
    public String insertQna(Qna qna){

        QnaService.insertQna(qna);

        return "redirect:/qna/qnaListView";
    }
}
