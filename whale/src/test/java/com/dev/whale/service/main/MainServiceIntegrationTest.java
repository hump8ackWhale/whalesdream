package com.dev.whale.service.main;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.main.MainRepository;
import com.dev.whale.repository.myPage.MyPageRepository;
import com.dev.whale.service.MainService;
import com.dev.whale.service.MyPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
@Transactional
public class MainServiceIntegrationTest {

    @Autowired
    MainService mainService;
    @Autowired MainRepository mainRepository;

    @Test
    public void joinUser() throws Exception {
        //Given
        User user = new User();
        user.setUserId("spring");
        user.setUserName("hello");
        user.setMail("testjoin@google.com");
        user.setJoinDate(new Date());
        user.setTermYn("N");

        //When
        mainService.join(user);


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
