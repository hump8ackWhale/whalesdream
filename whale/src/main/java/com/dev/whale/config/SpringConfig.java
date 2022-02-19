package com.dev.whale.config;

import com.dev.whale.repository.account.AccountRepository;
import com.dev.whale.repository.account.JpaAccountRepository;
import com.dev.whale.repository.main.JpaMainRepository;
import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.repository.myPage.JpaMyPageRepository;
import com.dev.whale.repository.myPage.MyPageRepository;
import com.dev.whale.repository.post.JpaPostRepository;
import com.dev.whale.repository.qna.JpaQnaRepository;
import com.dev.whale.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public AccountService accountService() {
        return new AccountService(accountRepository());
    }

    @Bean
    public MainService mainService() {
        return new MainService(mainRepository());
    }

    @Bean
    public MyPageService myPageService() {
        return new MyPageService(myPageRepository());
    }

    @Bean
    public QnaService qnaService() {
        return new QnaService(qnaRepository());
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public AccountRepository accountRepository() {
        return new JpaAccountRepository(em);
    }

    @Bean
    public MainRepository mainRepository() {
        return new JpaMainRepository(em);
    }

    @Bean
    public MyPageRepository myPageRepository() {
        return new JpaMyPageRepository(em);
    }

    @Bean
    public JpaQnaRepository qnaRepository() {
        return new JpaQnaRepository(em);
    }

    @Bean
    public JpaPostRepository postRepository() {
        return new JpaPostRepository(em);
    }
}
