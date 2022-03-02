package com.dev.whale.repository.myPage;

import com.dev.whale.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface MyInfoRepository {
    User selectUserInfo(String username);
}
