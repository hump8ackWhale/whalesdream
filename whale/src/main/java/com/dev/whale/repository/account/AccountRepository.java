package com.dev.whale.repository.account;

import com.dev.whale.domain.model.User;

import java.util.Optional;

public interface AccountRepository {
    User join(User user);
    Optional<User> findByName(String userName);
}
