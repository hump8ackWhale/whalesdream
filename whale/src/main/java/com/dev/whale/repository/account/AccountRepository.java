package com.dev.whale.repository.account;

import com.dev.whale.domain.model.User;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository {

    // 회원가입
    User join(User user);

    // 사용자 조회
    Optional<User> findByName(String userName);

    // 비밀번호 변경
    @Modifying(clearAutomatically = true)   // -> 영속성 컨텍스트 clear 해줘서 기존 엔티티 날라가도록. select 결과(db값) 조회
    int updateUserTempPassword(String str, Long id);

    void changePw(String username, String orgPw, String newPw);
}
