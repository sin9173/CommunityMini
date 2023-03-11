package com.task.zari.dto.request.board.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityModifyRequestDto { //커뮤니티 글 수정 요청데이터
    
    private String title; // 글 제목
    
    private String content; //글 내용
}
