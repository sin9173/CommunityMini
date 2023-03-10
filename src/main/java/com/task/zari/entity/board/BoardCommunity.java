package com.task.zari.entity.board;

import com.task.zari.entity.account.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMMUNITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommunity extends Board {

    public BoardCommunity(String title, String content, Account account) {
        super(title, content, account);
    }
}
