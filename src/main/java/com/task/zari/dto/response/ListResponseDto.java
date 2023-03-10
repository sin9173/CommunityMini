package com.task.zari.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ListResponseDto <T> extends ResponseDto { //리스트 응답

    private List<T> data;

    public ListResponseDto(ResponseResult result, List<T> data) {
        super(result);
        this.data = data;
    }
}
