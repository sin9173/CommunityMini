package com.task.zari.auth;

import com.task.zari.entity.Quit;
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
        return accountRepository.findByAccountId(username)
                .filter(user -> user.getQuit()== Quit.ACTIVE)
                .map(user -> new PrincipalDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }
}
