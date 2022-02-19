package com.dev.whale.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private String loginUsername;
    private String loginPassword;
    private String loginRedirectUrl;
    private String exceptionMsg;
    private String defaultFailureUrl;

    public LoginFailureHandler() {
        this.loginUsername = "username";    // loginForm의 loginParameter
        this.loginPassword = "password";    // loginForm의 passwordParameter
        this.loginRedirectUrl = "loginRedirect";
        this.exceptionMsg = "securityexceptionmsg";
        this.defaultFailureUrl = "/";
    }

    /**
     * 인증에 실패할 경우 아래 매서드로 이동.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // Request 객체의 Attribute에 로그인 실패했을 때의 id, 비밀번호를 저장해두고 실패시 url의 페이지(로그인 페이지)에서 이를 접근하도록 함
        String username = request.getParameter(loginUsername);
        String password = request.getParameter(loginPassword);
        String redirectUrl = request.getParameter(loginRedirectUrl);

        request.setAttribute(loginUsername, username);
        request.setAttribute(loginPassword, password);
        request.setAttribute(loginRedirectUrl, redirectUrl);
        request.setAttribute(exceptionMsg, exception.getMessage());

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
    }
}
