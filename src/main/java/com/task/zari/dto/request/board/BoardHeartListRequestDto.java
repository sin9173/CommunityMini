package com.task.zari.dto.request.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardHeartListRequestDto { //특정 아이디가 좋아요한 게시글 조회 요청데이터

    private int page; //페이지

    private String account_id; //계정아이디
}
