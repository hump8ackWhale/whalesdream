package com.dev.whale.controller;

import com.dev.whale.domain.MailVO;
import com.dev.whale.domain.model.User;
import com.dev.whale.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
    }

    @GetMapping("/findPw")
    public String goFindPw() {
        return "account/findPassword";
    }

    @GetMapping("/check/findUsername")
    public @ResponseBody String findByEmail(String userEmail) {
        accountService.findByEmail(userEmail);

        return "index";
    }

    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> findPw(String userEmail, String userName) {
        Map<String, Boolean> map = new HashMap<>();
        boolean findCheck = accountService.usernameCheck(userEmail, userName);

        map.put("check", findCheck);
        return map;
    }

    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody String sendEmail(String userEmail, String userName) {
        MailVO mail = accountService.changePasswordMail(userEmail, userName);
        accountService.mailSend(mail);

        return "index";
    }

    @GetMapping("/goChangePw")
    public String goChangePw(HttpServletRequest request, Model model) {
        model.addAttribute("userNo", request.getParameter("id"));
        return "account/changePassword";
    }

    @GetMapping("/changePw")
    public @ResponseBody Boolean changePw(HttpServletRequest request, HttpServletResponse response, Long id, String orgPw, String newPw) {
        boolean result = accountService.changePw(id, orgPw, newPw);

        // 비밀번호 수정 후 다시 로그인하게 할 경우 세션 비워줘야함
        if (result) {
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                    .getContext().getAuthentication());
        }

        return result;
    }

    @GetMapping("/leaveAcnt")
    public @ResponseBody String updateTerm(HttpServletRequest request, String password, Long id) {
        boolean result = accountService.checkPw(password, id);

        if (result) {
            SecurityContextHolder.clearContext();
        }

        return "index";
    }

}