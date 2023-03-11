package com.task.zari.dto.request.board.heart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeartSaveRequestDto { //좋아요 등록 요청데이터

    private Long board_id; //게시글 인덱스
}
