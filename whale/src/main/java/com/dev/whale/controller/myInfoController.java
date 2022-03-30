package com.dev.whale.controller;

import com.dev.whale.domain.model.User;
import com.dev.whale.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/myInfo")
public class myInfoController {

    private final MyInfoService myInfoService;

    @Autowired
    public myInfoController(MyInfoService myInfoService) {
        this.myInfoService = myInfoService;
    }

    @GetMapping("/myForm")
    public String myInfo(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User myInfo = myInfoService.selectUserInfo(auth.getName());

        model.addAttribute("myInfo", myInfo);

        return "myInfo/myForm";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam String newVal, @RequestParam Long id) {

        User myInfo = myInfoService.updateUserInfo(newVal, id);

        model.addAttribute("myInfo", myInfo);

        return "redirect:/";
    }

    /**
    * @package  : com.dev.whale.controller
    * @author   : kimzoo2
    * @date     : 2022-03-03 오후 6:29
    * @version  : 1.0.0
    * @modified :
    **/
    @RequestMapping("/leaveAccount")
    public String leaveAccount(HttpServletRequest request, Model model) {

        String id = request.getParameter("id");

        model.addAttribute("userNo", id);

        return "myInfo/leaveAccount";
    }
}
