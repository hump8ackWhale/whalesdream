package com.dev.whale.service;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.MainRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class MainServiceIntegrationTest {

    @Autowired MainService mainService;
    @Autowired MainRepository mainRepository;

    @Test
    public void findUserId() throws Exception {
        List<User> list = mainService.findUserList();
        System.out.print(list.get(0).getUserId());
    }

    @Test
    public void joinUser() throws Exception {
        //Given
        User user = new User();
        user.setUserId("spring");
        user.setUserName("hello");
        user.setMail("testjoin@google.com");

        //When
        mainService.join(user); // 미친놈안됨

        //Then
    }
/*    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }*/
}
