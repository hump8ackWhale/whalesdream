package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.repository.myPage.MyPageRepository;

import javax.transaction.Transactional;

@Transactional
public class MyPageService {

    private final MyPageRepository myPageRepository;

    public MyPageService(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    // 회원정보(마이페이지)
    public User userInfo(int userNo) {
        return myPageRepository.userInfo(userNo);
    }
}
