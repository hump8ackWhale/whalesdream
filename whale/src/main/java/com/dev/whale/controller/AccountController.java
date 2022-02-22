package com.dev.whale.controller;

import com.dev.whale.domain.model.User;
import com.dev.whale.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @PostMapping("/loginFail")
//    public String login() {
//        return "index";
//    }

    @PostMapping("/loginFail")
    public String loginFail(HttpServletRequest request, HttpServletResponse response) {
        String error = request.getParameter("errorMessage");
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        accountService.join(user);

        return "index";
    }
}