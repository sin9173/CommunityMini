package com.task.zari.dto.request.account;

import com.task.zari.entity.account.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AccountSaveRequestDto {

    @NotBlank
    private String account_id; //계정 ID

    @NotBlank
    private String password; //비밀번호

    @NotBlank
    private String nickname; //닉네임

    private AccountType account_type; //계정 타입

}
