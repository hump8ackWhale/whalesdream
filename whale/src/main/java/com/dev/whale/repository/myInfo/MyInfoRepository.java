package com.dev.whale.repository.myInfo;

import com.dev.whale.domain.model.User;

public interface MyInfoRepository {

    // 회원 정보 조회
    User selectUserInfo(String username);

    // 회원 정보 수정
    User updateUserInfo(String newVal, Long id);
}
