package com.dev.whale;

import com.dev.whale.repository.main.JpaMainRepository;
import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.repository.myPage.JpaMyPageRepository;
import com.dev.whale.repository.myPage.MyPageRepository;
import com.dev.whale.service.MainService;
import com.dev.whale.service.MyPageService;
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
    public MainService mainService() {
        return new MainService(mainRepository());
    }

    @Bean
    public MyPageService myPageService() {
        return new MyPageService(myPageRepository());
    }

    @Bean
    public MainRepository mainRepository() {
        return new JpaMainRepository(em);
    }

    @Bean
    public MyPageRepository myPageRepository() {
        return new JpaMyPageRepository(em);
    }
}
