package com.dev.whale.controller;

import com.dev.whale.domain.MailVO;
import com.dev.whale.domain.model.User;
import com.dev.whale.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/loginFail")
    public String loginFail(HttpServletRequest request, HttpServletResponse response) {
        String error = request.getParameter("errorMessage");
        return "index";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

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

    @GetMapping("/goPwPage")
    public void goPwPage(String username) {
        String issueYn = accountService.selectIssueYn(username);

        if (issueYn.equals("N")) {
            goFindPw();
        } else {
            goChangePw();
        }
    }

    @GetMapping("/findPw")
    public String goFindPw() {
        return "account/findPassword";
    }

    // email, username 일치 여부
    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> findPw(String userEmail, String userName) {
        Map<String, Boolean> map = new HashMap<>();
        boolean pwFindCheck = accountService.userNameCheck(userEmail, userName);

        map.put("check", pwFindCheck);
        return map;
    }

    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody String sendEmail(String userEmail, String userName) {
        MailVO mail = accountService.changePasswordMail(userEmail, userName);
        accountService.mailSend(mail);

        return "index";
    }

    @GetMapping("/goChangePw")
    public String goChangePw() {
        return "account/changePassword";
    }

    // newPw != ReNewPw 가 일치하지 않을 경우 화면단에서 메세지 띄워주고
    // 입력한 비밀번호와 db 비밀번호가 일치하지 않을 경우 ajax로 바로 메세지 띄워주기
    @PostMapping("/changePw")
    public @ResponseBody String changePw(Model model, String username, String orgPw, String newPw) {
        boolean result = accountService.changePw(username, orgPw, newPw);

        if (result) {
            return "index";
        } else {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "redirect:/";
        }
    }
}