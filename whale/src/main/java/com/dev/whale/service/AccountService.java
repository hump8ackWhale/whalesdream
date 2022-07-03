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
import java.util.List;
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

    // 아이디 중복 검증
    public String validateDuplicateUser(String username, String email, String flag) {
        String result = "";

        if (flag.equals("E")) {
            Optional<User> emailCheck = accountRepository.findByEmail(email);
            if (emailCheck.isPresent()) {
                result = "중복된 이메일입니다.";
            } else {
                result = "가입 가능한 이메일입니다.";
            }
        } else {
            Optional<User> idCheck = accountRepository.findByName(username);
            if (idCheck.isPresent()) {
                result = "중복된 아이디입니다.";
            } else {
                result = "가입 가능한 아이디입니다.";
            }
        }

        return result;
    }

    // 회원 가입
    public void join(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        accountRepository.join(user);
    }

    // 해당 userEmail의 사용자가 있는지 CHECK
    public Boolean findByEmail(String userEmail) {
        Optional<User> user = accountRepository.findByEmail(userEmail);

        if (user.isPresent()) {
            MailVO mail = usernameMail(user);
            mailSend(mail);
            return true;
        } else {
            return false;
        }
    }

    // 메일 내용 세팅
    public MailVO usernameMail(Optional<User> user) {
        MailVO mail = new MailVO();

        mail.setAddress(user.get().getEmail());
        mail.setTitle("Whale Of Dream 계정 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. Whale Of Dream 아이디 안내 관련 이메일 입니다.\n"
                + "가입하신 계정의 아이디는 " + user.get().getUsername() + " 입니다.");

        return mail;
    }

    // 해당 userEmail, userName의 사용자가 있는지 CHECK
    public boolean usernameCheck(String userEmail, String username) {
        Optional<User> user = accountRepository.findByName(username);

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
        mail.setTitle(userName + "님의 Whale Of Dream 계정 비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. Whale Of Dream 임시 비밀번호 안내 관련 이메일 입니다.\n"
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

    // 임시 비밀번호 발급 여부
    public String selectIssueYn(String username) {
        return accountRepository.findByName(username).get().getIssueYn();
    }

    // 암호화된 비밀번호 조회
    public Boolean changePw(Long id, String orgPw, String newPw) {

        String checkOrgPw = accountRepository.findById(id).getPassword();

        // 임시 비밀번호와 화면에서 입력한 임시 비밀번호가 다르다면 false
        if (passwordEncoder.matches(orgPw, checkOrgPw)) {

            String encodeNewPw = passwordEncoder.encode(newPw);
            accountRepository.changePw(id, encodeNewPw);
            return true;
        }

        return false;
    }

    // 회원 탈퇴
    public Boolean checkPw(String password, Long id) {
        String orgPw = accountRepository.findById(id).getPassword();

        if (passwordEncoder.matches(password, orgPw)) {
            accountRepository.updateTerm(id);
            return true;
        }

        return false;
    }
}
