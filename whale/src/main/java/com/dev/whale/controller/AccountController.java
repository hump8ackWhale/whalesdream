package com.dev.whale.controller;

import com.dev.whale.domain.MailVO;
import com.dev.whale.domain.model.User;
import com.dev.whale.service.AccountService;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        //this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/goRegister")
    public String goRegister() {
        return "account/register";
    }

    @GetMapping("/dupCheck")
    @ResponseBody
    public JSONObject idCheck(@RequestParam(required = false) String username, @RequestParam(required = false) String email, @RequestParam String flag) {
        JSONObject json = new JSONObject();
        String result = accountService.validateDuplicateUser(username, email, flag);

        json.put("result", result);
        return json;
    }

    @GetMapping("/register")
    public String register(User user) {
        accountService.join(user);
        return "main/main";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());

        return "index";
    }

    @GetMapping("/findPw")
    public String goFindPw() {
        return "account/findPassword";
    }

    @GetMapping("/check/findUsername")
    public @ResponseBody Map<String, Boolean> findByEmail(@RequestParam String userEmail) {
        Map<String, Boolean> map = new HashMap<>();

        boolean findName = accountService.findByEmail(userEmail);

        map.put("check", findName);
        return map;
    }

    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> findPw(@RequestParam String userEmail, @RequestParam String userName) {
        Map<String, Boolean> map = new HashMap<>();

        boolean findPw = accountService.usernameCheck(userEmail, userName);

        map.put("check", findPw);
        return map;
    }

    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody String sendEmail(@RequestParam String userEmail, @RequestParam String userName) {
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
    public String changePw(HttpServletRequest request, @RequestParam Long id, @RequestParam String orgPw, @RequestParam String newPw) {
        boolean result = accountService.changePw(id, orgPw, newPw);

        // 비밀번호 수정 후 다시 로그인하게 할 경우 세션 비워줘야함
        if (result) {
            // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            // Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials());
            // SecurityContextHolder.getContext().setAuthentication(newAuth); => 참고용, 회원의 권한 등의 정보를 로그아웃 없이 갱신

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            SecurityContextHolder.clearContext();

            return "index";
        }

        return "redirect:/";
    }

    @GetMapping("/leaveAcnt")
    public String updateTerm(HttpServletRequest request, @RequestParam String password, @RequestParam Long id) {
        boolean result = accountService.checkPw(password, id);

        if (result) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            SecurityContextHolder.clearContext();
        }

        return "index";
    }

    private static final String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    /*
    @Autowired
    private final ClientRegistrationRepository clientRegistrationRepository;

    // Lombok 아닌 경우 (@RequiredArgsConstructor 없는 경우)
    // @Autowired private ClientRegistrationRepository clientRegistrationRepository;
    @GetMapping("/login/naver")
    public String getLoginPage(Model model) throws Exception {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        assert clientRegistrations != null;
        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "account/oauth-login";
    }

     */
}