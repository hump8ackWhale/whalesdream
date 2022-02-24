package com.dev.whale.config.auth;

import com.dev.whale.domain.model.User;
import com.dev.whale.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public PrincipalDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = accountRepository.findByName(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다.:" + username);
                });
        return new PrincipalDetail(principal);
    }
}
