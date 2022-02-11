package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;
import org.springframework.ui.Model;

import javax.transaction.Transactional;

@Transactional
public class MainService {

    private final MainRepository mainRepository;

    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    // 회원 가입
    public String join(User user) {
        // 아이디 중복 x
        validateUserId(user);
        mainRepository.save(user);
        return user.getUserId();
    }

    // 아이디 중복 검증
    private void validateUserId(User user) {
        mainRepository.findById(user.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 아이디입니다.");
                });
    }

    public User selectUser(User user) {
        return mainRepository.selectUser(user);
    }

    public void insertUser(User user) {
        validateUserId(user);
        mainRepository.insertUser(user);
    }

}
