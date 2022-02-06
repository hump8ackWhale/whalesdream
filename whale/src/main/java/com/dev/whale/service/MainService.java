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

    // ȸ�� ����
    public String join(User user) {
        // ���̵� �ߺ� x
        validateUserId(user);
        mainRepository.save(user);
        return user.getUserId();
    }

    // ���̵� �ߺ� ����
    private void validateUserId(User user) {
        Optional.ofNullable(mainRepository.findById(user.getUserId()))
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
}
