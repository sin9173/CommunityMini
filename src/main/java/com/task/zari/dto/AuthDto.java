package com.task.zari.dto;

import com.task.zari.entity.Quit;
import com.task.zari.entity.account.Account;
import com.task.zari.entity.account.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthDto {

    private String username;

    private String password;

    private String nickname;

    private AccountType accountType;

    public AuthDto(Account account) {
        if(account.getQuit() == Quit.QUIT) throw new RuntimeException("탈퇴한 계정입니다.");
        this.username = account.getAccountId();
        this.password = account.getPassword();
        this.nickname = account.getNickname();
        this.accountType = account.getAccountType();
    }
}
