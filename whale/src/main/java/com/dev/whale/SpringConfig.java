package com.dev.whale;

import com.dev.whale.repository.MainRepository;
import com.dev.whale.repository.MainRepositoryImpl;
import com.dev.whale.service.MainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MainService mainService() {
        return new MainService(mainRepository());
    }

    @Bean
    public MainRepository mainRepository() {
        return new MainRepositoryImpl();
    }
}
