package com.dev.whale.config;

import com.dev.whale.config.auth.PrincipalDetailService;
import com.dev.whale.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity  // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    private PrincipalDetailService principalDetailService;

    @Autowired
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, PrincipalDetailService principalDetailService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.principalDetailService = principalDetailService;
    }

    @Autowired
    private DataSource dataSource;

    // 정적 파일
    private static final String[] AUTH_WHITELIST = {
            "/assets/**",
            "/assets/images/**",
            "/css/**",
            "/fonts/Assistant/**",
            "/fonts/Rubik/**",
            "/js/**"
    };

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/", "/account/**").permitAll() // 비로그인에도 접근 가능한 url
                    .anyRequest().authenticated() // 위의 url 외의 url은 로그인 필수
                    .and()
                .formLogin()
                    .loginPage("/") // 로그인이 필요한 페이지를 접속할 경우 해당 url로 이동
                    .successHandler(successHandler())
                    .failureHandler(failureHandler())
                    .permitAll() // 로그인페이지는 모두 접근 가능
                    .and()
                .logout()
                    .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true)  // 세션 날리기
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled " // 인증
                        + "from tb_user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, r.name " // 권한
                        + "from user_role ur inner join tb_user u on ur.user_id = u.id "
                        + "inner join tb_role r on ur.role_id = r.id "
                        + "where u.username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new LoginFailureHandler();
    }
}
