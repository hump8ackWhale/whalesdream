package com.dev.whale.config;

import com.dev.whale.config.auth.PrincipalDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    // 화면 이동에 대한 규칙을 정의하는 인터페이스
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String targetUrlParameter;
    private String defaultUrl;
    private boolean useReferer;

    public LoginSuccessHandler() {
        targetUrlParameter = "";
        defaultUrl = "";
        useReferer = false;

    }

    public String getTargetUrlParameter() {
        return targetUrlParameter;
    }

    public void setTargetUrlParameter(String targetUrlParameter) {
        this.targetUrlParameter = targetUrlParameter;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public boolean isUseReferer() {
        return useReferer;
    }

    public void setUseReferer(boolean useReferer) {
        this.useReferer = useReferer;
    }

    /**
     * 인증에 성공할 경우 아래 매서드로 이동.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        clearAuthenticationAttribute(request);
        useDefaultUrl(request, response, authentication);
/*        int intRedirectStrategy = decideRedirectStrategy(request, response);
        switch (intRedirectStrategy) {
            case 1:
                useTargetUrl(request, response);
                break;
            case 2:
                useSessionUrl(request, response);
                break;
            case 3:
                useRefererUrl(request, response);
                break;
            default:
                useDefaultUrl(request, response);
                break;
        }*/


    }

    // 로그인이 실패했을 때의 에러 세션 clear
    private void clearAuthenticationAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private int decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response) {
        int result;

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (!"".equals(targetUrlParameter)) {
            String targetUrl = request.getParameter(targetUrlParameter);
            if (StringUtils.hasText(targetUrl)) {
                result = 1;
            } else {
                if (savedRequest != null) {
                    result = 2;
                } else {
                    String refererUrl = request.getHeader("REFERER");
                    if (useReferer && StringUtils.hasText(refererUrl)) {
                        result = 3;
                    } else {
                        result = 0;
                    }
                }
            }
            return  result;
        }

        if (savedRequest != null) {
            result = 2;
            return result;
        }

        String refererUrl = request.getHeader("REFERER");
        if (useReferer && StringUtils.hasText(refererUrl)) {
            result = 3;
        } else {
            result = 0;
        }

        return result;
    }

    // 이동할 url이 있을 경우 request 객체에서 값을 읽어 URL이 존재하면 이동
    private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            requestCache.removeRequest(request, response);
        }
        String targetUrl = request.getParameter(targetUrlParameter);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl = savedRequest.getRedirectUrl();
        redirectStrategy.sendRedirect(request, response, targetUrl);

    }

    private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetUrl = request.getHeader("REFERER");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 임시 비밀번호 발급 여부 Y 면 비밀번호 변경 화면으로 REDIRECT
        if (((PrincipalDetail) (authentication).getPrincipal()).getUser().getIssueYn().equals("Y")) {
            defaultUrl = "/account/goChangePw?id=" + ((PrincipalDetail) (authentication).getPrincipal()).getUser().getId();
        } else {
            defaultUrl = "/main/goMainPage";
        }
        redirectStrategy.sendRedirect(request, response, defaultUrl);
    }
}
