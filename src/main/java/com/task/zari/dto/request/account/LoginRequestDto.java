package com.task.zari.dto.request.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginRequestDto { //로그인 요청데이터

    @NotBlank
    private String account_id; //계정 아이디

    @NotBlank
    private String password; //비밀번호
}
