package com.task.zari.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseResult { //응답코드, 메세지

    SUCCESS(0, "성공"),
    ACCOUNT_DUPLICATE(40901, "중복된 계정 아이디입니다."),
    HEART_DUPLICATE(40902, "같은 게시글에 좋아요를 두번이상 할 수 없습니다."),
    NOT_VALID(40001, "입력이 누락되었거나 유효하지 않습니다."),
    DATA_INTEGRITY(40002, "길이 또는 값이 잘못되었습니다."),
    USERNAME_NOT_FOUND(40301, "존재하지 않는 계정입니다."),
    BAD_CREDENTIAL(40102, "계정정보가 잘못되었습니다."),
    NOT_WRITE_ACCOUNT(40103, "작성자의 계정이 아닙니다."),
    NOT_AUTHENTICATION(40103, "인증에 실패했습니다."),
    FORBIDDEN(40301, "접근 권한이 없습니다."),
    DATA_NOT_FOUND(40401, "데이터가 없습니다.");

    private int code;

    private String message;
}
