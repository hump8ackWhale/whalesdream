package com.dev.whale.controller;

import com.dev.whale.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/main")
    public String userList(Model model) {
        // List<User> userList = mainService.getUserList();
        // model.addAttribute("users", userList);

        return "main/APIExamNaverLogin";
    }
}