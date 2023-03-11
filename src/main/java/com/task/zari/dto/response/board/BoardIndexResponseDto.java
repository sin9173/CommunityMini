package com.task.zari.dto.response.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardIndexResponseDto {

    private Long id;

    public BoardIndexResponseDto(Long id) {
        this.id = id;
    }
}
