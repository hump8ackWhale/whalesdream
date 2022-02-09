package com.dev.whale.repository.myPage;

import com.dev.whale.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface MyPageRepository {
    User userInfo(int userNo);
}
