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

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원 가입
    public void join(User user) {
        // 아이디 중복 x
        validateDuplicateUser(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        mainRepository.join(user);
    }

    // 아이디 중복 검증
    private void validateDuplicateUser(User user) {
        mainRepository.findByName(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 아이디입니다.");
                });
    }
}
