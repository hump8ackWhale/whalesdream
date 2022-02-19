package com.dev.whale.service;

import com.dev.whale.domain.model.Role;
import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@Transactional
public class MainService {

    private final MainRepository mainRepository;

    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

}
