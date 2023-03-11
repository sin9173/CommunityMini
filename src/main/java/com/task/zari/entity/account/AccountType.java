package com.task.zari.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType { //계정타입 Value

    Lessor("임대인"),
    Realtor("공인 중계사"),
    Lessee("임차인");

    private String name;
}
