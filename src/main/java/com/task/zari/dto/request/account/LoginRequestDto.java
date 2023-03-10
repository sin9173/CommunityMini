package com.task.zari.dto.request.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginRequestDto {

    @NotBlank
    private String account_id;

    @NotBlank
    private String password;
}
