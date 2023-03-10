package com.task.zari.repository.account;

import com.task.zari.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByAccountId(String accountId);
}
