package com.dev.whale.repository.account;

import com.dev.whale.domain.model.User;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);

    User findById(Long id);

    // 회원가입
    User join(User user);

    // 비밀번호 변경
    @Modifying(clearAutomatically = true)   // -> 영속성 컨텍스트 clear 해줘서 기존 엔티티 날라가도록. select 결과(db값) 조회
    int updateUserTempPassword(String str, Long id);

    // 비밀번호 변경
    void changePw(Long id, String encodeNewPw);

    // 회원 탈퇴(term_yn = y)
    void updateTerm(Long id);
}
