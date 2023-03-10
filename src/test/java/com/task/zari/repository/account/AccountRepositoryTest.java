package com.task.zari.repository.account;

import com.task.zari.entity.account.Account;
import com.task.zari.entity.account.AccountType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    @Transactional
    public void accountSaveTest() {
        Account account = new Account("aaa123", passwordEncoder.encode("password"),
                "nickname", AccountType.LESSEE);

        accountRepository.save(account);

        account.delete();

        Assertions.assertThat(account.getAccountType().getName()).isEqualTo("임차인");
    }
}