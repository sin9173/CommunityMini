package com.task.zari.repository.account;

import com.task.zari.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByAccountId(String accountId);
}
