package com.task.zari.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto { //응답

    private boolean success; //성공 여부

    private int code; //응답 코드

    private String message; //응답 메세지

    public ResponseDto(ResponseResult result) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.success = this.code==0?true:false;
    }
}
