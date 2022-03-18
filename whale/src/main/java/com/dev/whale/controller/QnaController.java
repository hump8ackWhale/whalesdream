package com.dev.whale.controller;

import com.dev.whale.domain.model.Category;
import com.dev.whale.domain.model.Qna;
import com.dev.whale.domain.model.Reply;
import com.dev.whale.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String qnaListView(Model model){

        List<Qna> qnaList = QnaService.selectMyQnaList();

        if (qnaList.size() > 0) {
            model.addAttribute("qnaList", qnaList);
        }

        return "qna/qnaListView";
    }


    @GetMapping("/qnaDetailView/{qnaNo}")
    public String qnaListView(@PathVariable("qnaNo") int qnaNo, Model model){

        Qna qnaDetail = QnaService.findById(qnaNo);

        List<Reply> replyList = QnaService.selectQnaReply(qnaNo);

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

    @GetMapping("/qnaModifyForm")
    public String qnaModifyForm(Model model, Integer nno){

        Qna qnaDetail = QnaService.findById(nno);

        List<Category> category = QnaService.selectCategory();

        model.addAttribute("categoryList", category);
        model.addAttribute("qnaDetail", qnaDetail);

        return "qna/qnaEnrollForm";
    }

    @GetMapping("/qnaUpdate")
    public String update (RedirectAttributes redirect, Qna qna){

        QnaService.update(qna);
        redirect.addAttribute("username", qna.getUsername());
        return "redirect:/qna/qnaListView";
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

    @GetMapping("/qnaDelete/{qnaNo}")
    public String delete (@PathVariable("qnaNo") int qnaNo){

        Qna qna = new Qna();
        qna.setQnaNo(qnaNo);

        QnaService.deleteById(qna);
        return "redirect:/qna/qnaListView";
    }

    @GetMapping("/replyDelete/{replyNo}")
    public String replyDelete (@PathVariable("replyNo") int replyNo){

        Reply reply = new Reply();
        reply.setReplyNo(replyNo);

        QnaService.delete(reply);
        return "redirect:/qna/qnaListView";
    }
}
