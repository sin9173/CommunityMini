package com.task.zari.config.auth;

import com.task.zari.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("로그인 시도 : " + username);
        return accountRepository.findByAccountId(username)
                .map(user -> new PrincipalDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }
}
