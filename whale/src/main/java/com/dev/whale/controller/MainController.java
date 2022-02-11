package com.dev.whale.controller;

import com.dev.whale.domain.model.User;
import com.dev.whale.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/join")
    public String joinUser(User user) {
        user.setJoinDate(new Date());
        user.setTermYn("N");
        mainService.insertUser(user);

        return "login";
    }

    @RequestMapping("/login")
    public String getUser(User user, Model model) {
        User loginUser = mainService.selectUser(user);
        model.addAttribute("user", loginUser);

        return "loginSuccess";
    }
}