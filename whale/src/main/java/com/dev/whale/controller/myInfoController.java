package com.dev.whale.controller;

import com.dev.whale.domain.model.User;
import com.dev.whale.service.AccountService;
import com.dev.whale.service.MyInfoService;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
