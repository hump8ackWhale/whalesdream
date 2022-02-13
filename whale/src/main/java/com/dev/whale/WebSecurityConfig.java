package com.dev.whale;

import com.dev.whale.service.MainService;
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

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // 정적 파일
    private static final String[] AUTH_WHITELIST = {
            "/assets/**",
            "/assets/images/**",
            "/css/maps/**",
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
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/account/login").permitAll() // 비로그인에도 접근 가능한 url
                    //.anyRequest().authenticated() // 위의 url 외의 url은 로그인 필수
                    .and()
                .formLogin()
                    .loginPage("/account/login") // 로그인 필수 페이지로 갈 경우 해당 url로 이동
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .permitAll() // 로그인페이지는 모두 접근 가능
                    .and()
                .logout()
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true); // 세션 날리기
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
