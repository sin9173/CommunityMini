package com.task.zari.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType { //계정타입 Value

    LESSOR("임대인"),
    REALTOR("공인 중계사"),
    LESSEE("임차인");

    private String name;
}
