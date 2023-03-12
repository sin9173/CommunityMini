package com.task.zari.dto.heart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeartCountDto {
    
    private Long board_id; //게시글 인덱스
    
    private Long heart_count; //게시글 좋아요 개수


    public HeartCountDto(Long board_id, Long heart_count) {
        this.board_id = board_id;
        this.heart_count = heart_count;
    }
}
