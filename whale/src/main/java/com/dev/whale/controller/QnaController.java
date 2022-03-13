package com.dev.whale.controller;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.Reply;
import com.dev.whale.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String qnaListView(@RequestParam String username, Model model){

        List<Qna> qnaList = QnaService.selectMyQnaList(username);

        if (qnaList.size() > 0) {
            model.addAttribute("qnaList", qnaList);
        }

        return "qna/qnaListView";
    }


    @GetMapping("/qnaDetailView")
    public String qnaListView(Model model, Integer nno){


        Qna qnaDetail = QnaService.selectMyQnaDetailView(nno);

        List<Reply> replyList = QnaService.selectQnaReply(nno);

        model.addAttribute("replyList", replyList);
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
    public String insertQna(RedirectAttributes redirect, Qna qna){

        QnaService.insertQna(qna);

        redirect.addAttribute("username", qna.getUsername());
        return "redirect:/qna/qnaListView";
    }

    @GetMapping("/insertReply")
    public String insertReply(RedirectAttributes redirect, Reply reply){

        System.out.println(reply);
        QnaService.insertReply(reply);

        redirect.addAttribute("nno", reply.getQnaNo());
        return "redirect:/qna/qnaDetailView";
    }
}
