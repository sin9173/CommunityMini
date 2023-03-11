package com.task.zari.dto.request.board.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CommunitySaveRequestDto { //커뮤티티 글작성 요청 데이터

    @NotBlank
    private String title; //제목

    @NotBlank
    private String content; //내용
    
}
