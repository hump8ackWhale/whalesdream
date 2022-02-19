package com.dev.whale.service.main;

import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class MainServiceIntegrationTest {

    @Autowired
    MainService mainService;
    @Autowired
    MainRepository mainRepository;
}
