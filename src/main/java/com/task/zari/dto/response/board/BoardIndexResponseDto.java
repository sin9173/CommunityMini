package com.task.zari.dto.response.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardIndexResponseDto { //게시글 등록, 수정 응답데이터

    private Long id; //게시글 인덱스

    public BoardIndexResponseDto(Long id) {
        this.id = id;
    }
}
