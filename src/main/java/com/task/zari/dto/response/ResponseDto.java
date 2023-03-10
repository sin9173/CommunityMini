package com.task.zari.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto { //응답

    private boolean success;

    private int code;

    private String message;

    public ResponseDto(ResponseResult result) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.success = this.code==0?true:false;
    }
}
