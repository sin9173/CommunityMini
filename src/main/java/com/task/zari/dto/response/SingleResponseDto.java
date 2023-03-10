package com.task.zari.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SingleResponseDto<T> extends ResponseDto { //데이터 응답

    private T data;

    public SingleResponseDto(ResponseResult result, T data) {
        super(result);
        this.data = data;
    }
}
