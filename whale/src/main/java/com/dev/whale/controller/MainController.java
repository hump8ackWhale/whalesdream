package com.dev.whale.controller;

import com.dev.whale.domain.model.User;
import com.dev.whale.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

    @GetMapping("/register")
    public String register() {
        return "main/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        mainService.join(user);

        return "index";
    }
}