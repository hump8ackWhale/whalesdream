package com.dev.whale;

import com.dev.whale.repository.JpaMainRepository;
import com.dev.whale.repository.MainRepository;
import com.dev.whale.service.MainService;
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
    public MainRepository mainRepository() {
        return new JpaMainRepository(em);
    }
}
