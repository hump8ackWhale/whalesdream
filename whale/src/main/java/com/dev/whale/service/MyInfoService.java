package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.myInfo.MyInfoRepository;

import javax.transaction.Transactional;

@Transactional
public class MyInfoService {

    private final MyInfoRepository myInfoRepository;

    public MyInfoService(MyInfoRepository myInfoRepository) {
        this.myInfoRepository = myInfoRepository;
    }

    // 회원 정보
    public User selectUserInfo(String username) {
        return myInfoRepository.selectUserInfo(username);
    }

    // 회원 정보 수정
    public User updateUserInfo(String newVal, Long id) {
        return myInfoRepository.updateUserInfo(newVal, id);
    }
}
