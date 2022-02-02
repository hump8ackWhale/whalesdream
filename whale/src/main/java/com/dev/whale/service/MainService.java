package com.dev.whale.service;

import com.dev.whale.domain.User;
import com.dev.whale.repository.MainRepository;

import java.util.List;
import java.util.Optional;

public class MainService {

    private final MainRepository mainRepository;

    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    // ȸ�� ����
    public String join(User user) {
        // ���̵� �ߺ� x
        validateUserId(user);
        mainRepository.save(user);
        return user.getUserId();
    }

    // ���̵� �ߺ� ����
    private void validateUserId(User user) {
        mainRepository.findById(user.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("�ߺ��� ���̵��Դϴ�.");
                });
    }

    // ��ü ȸ�� ��ȸ
    public List<User> findUserList() {
        return mainRepository.findAll();
    }

    // userId ��ȸ
    public Optional<User> findUser(String userId) {
        return mainRepository.findById(userId);
    }

    public List<User> getUserList() {
        return mainRepository.getUserList();
    }
}
