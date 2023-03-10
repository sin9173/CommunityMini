package com.task.zari.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseResult { //응답코드, 메세지

    SUCCESS(0, "성공"),
    ACCOUNT_DUPLICATE(40901, "중복된 계정 아이디입니다."),
    NOT_VALID(40001, "입력이 누락되었습니다."),
    DATA_INTEGRITY(40002, "길이 또는 값이 잘못되었습니다.");

    private int code;

    private String message;
}
