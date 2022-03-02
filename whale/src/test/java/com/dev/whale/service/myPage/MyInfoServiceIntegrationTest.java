package com.dev.whale.service.myPage;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.myPage.MyInfoRepository;
import com.dev.whale.service.MyInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class MyInfoServiceIntegrationTest {

    @Autowired
    MyInfoService myInfoService;
    @Autowired
    MyInfoRepository myInfoRepository;

    @Test
    public void userInfo() throws  Exception {
        User info = myInfoService.selectUserInfo("mjyeo");
        System.out.print(info);
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
