package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.MainRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        Optional.ofNullable(mainRepository.findById(user.getUserId()))
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 아이디입니다.");
                });
    }

    // 전체 회원 조회
    public List<User> findUserList() {
        return mainRepository.findAll();
    }

    // userId 조회
    public Optional<User> findUser(String userId) {
        return mainRepository.findById(userId);
    }
}
