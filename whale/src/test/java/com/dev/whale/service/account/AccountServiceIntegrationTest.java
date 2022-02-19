package com.dev.whale.service.account;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.account.AccountRepository;
import com.dev.whale.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountServiceIntegrationTest {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void joinUser() throws Exception {

        String pass = "12345";

        //Given
        User user = new User();
        user.setUsername("mjyeo");
        user.setEmail("testjoin@google.com");
        user.setPassword(pass);

        //When
        accountService.join(user);
        //String encodePassword = accountRepository.findById(user.getId()).getPassword();

        //Then
    }
    @Test
    public void passwordEncoder() throws Exception {
        //Given
        String password = "12345";

        //When
        String encodePassword = passwordEncoder.encode(password);
       // System.out.print("암호화비밀번호 " + encodePassword);

        //then
        assertAll(
                () -> assertNotEquals(password, encodePassword),
                () -> assertTrue(passwordEncoder.matches(password, encodePassword))
        );
    }
}
