package com.dev.whale.service;

import com.dev.whale.domain.MailVO;
import com.dev.whale.domain.model.Role;
import com.dev.whale.domain.model.User;
import com.dev.whale.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "이메일 주소를 입력하세요.";

    // 회원 가입
    public void join(User user) {
        // 아이디 중복 x
        validateDuplicateUser(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        accountRepository.join(user);
    }

    // 아이디 중복 검증
    private void validateDuplicateUser(User user) {
        accountRepository.findByName(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 아이디입니다.");
                });
    }

    // 해당 파라미터의 사용자가 있는지 CHECK
    public boolean userNameCheck(String userEmail, String userName) {
        Optional<User> user = accountRepository.findByName(userName);

        if (user.isPresent() && user.get().getEmail().equals(userEmail)) {
            return true;
        } else {
            return false;
        }
    }

    // 메일 내용 세팅
    public MailVO changePasswordMail(String userEmail, String userName) {
        String str = getTempPassword();
        MailVO mail = new MailVO();

        mail.setAddress(userEmail);
        mail.setTitle(userName + "님의 Whale Of Dream 임시 비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. Whale Of Dream 임시 비밀번호 안내 관련 이메일 입니다."
                        + "[" +userName + "]" +"님의 임시 비밀번호는 " + str + " 입니다.");

        updateTempPassword(str, userName);

        return mail;
    }

    // 임시 비밀번호 생성
    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        return str;
    }

    // 임시 비밀번호로 UPDATE
    public void updateTempPassword(String str, String userName) {
        String pw = passwordEncoder.encode(str);
        Long id = accountRepository.findByName(userName).get().getId();

        accountRepository.updateUserTempPassword(pw, id);
    }

    // 메일 전송
    public void mailSend(MailVO mail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }
}
