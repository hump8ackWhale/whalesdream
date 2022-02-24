package com.dev.whale.controller;

import com.dev.whale.domain.MailVO;
import com.dev.whale.domain.model.User;
import com.dev.whale.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


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

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
        return "redirect:/index";
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "account/findPassword";
    }

    // email, username 일치 여부
    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> pwFind(String userEmail, String userName) {
        Map<String, Boolean> map = new HashMap<>();
        boolean pwFindCheck = accountService.userNameCheck(userEmail, userName);

        map.put("check", pwFindCheck);
        return map;
    }

    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody void sendEmail(String userEmail, String userName) {
        MailVO mail = accountService.changePasswordMail(userEmail, userName);
        accountService.mailSend(mail);
    }
}