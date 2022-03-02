package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.account.AccountRepository;
import com.dev.whale.repository.myPage.MyInfoRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class MyInfoService {

    private final MyInfoRepository myInfoRepository;

    public MyInfoService(MyInfoRepository myInfoRepository) {
        this.myInfoRepository = myInfoRepository;
    }

    // 회원정보(마이페이지)
    public User selectUserInfo(String username) {
        return myInfoRepository.selectUserInfo(username);
    }
}
