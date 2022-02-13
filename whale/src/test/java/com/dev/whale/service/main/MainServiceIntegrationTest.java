package com.dev.whale.service.main;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.repository.myPage.MyPageRepository;
import com.dev.whale.service.MainService;
import com.dev.whale.service.MyPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MainServiceIntegrationTest {

    @Autowired
    MainService mainService;
    @Autowired MainRepository mainRepository;
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
        mainService.join(user);
        //String encodePassword = mainRepository.findById(user.getId()).getPassword();

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
