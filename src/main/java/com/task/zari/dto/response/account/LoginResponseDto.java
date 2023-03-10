package com.task.zari.dto.response.account;

import com.task.zari.dto.AuthDto;
import com.task.zari.entity.account.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponseDto {

    private String account_id; //계정 아이디

    private String nickname; //닉네임

    private AccountType account_type; //계정타입

    public LoginResponseDto(AuthDto authDto) {
        this.account_id = authDto.getUsername();
        this.nickname = authDto.getNickname();
        this.account_type = authDto.getAccountType();
    }
}
